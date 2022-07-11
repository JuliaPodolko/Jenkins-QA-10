package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Tag("demoqa")
public class RegistrationFormTests extends TestBase {
     @Test
     @DisplayName("Successful fill registration test")
    void fillFormTest () {
        String firstName = "Julia",
                lastName = "Podolko",
                userEmail = "Julia@yandex.ru",
                userNumber = "9123456789",
                userSubject = "Maths",
                currentAddresses = "1st Street, 25",
                country = "Rajasthan",
                city = "Jaipur";

        String expectedFullName = format("%s %s", firstName, lastName);

        step("Open registration form", () -> {
                    open("/automation-practice-form");
                    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
                    executeJavaScript("$('footer').remove()");
                    executeJavaScript("$('#fixedban').remove()");
                });
        step("Fill registration form", () -> {
            $("#firstName").setValue(firstName); // Name
            $("#lastName").setValue(lastName); // Фамилия
            $("#userEmail").setValue(userEmail); // Электронная почта
            $("#userNumber").setValue(userNumber); // Номер телефона
            $("#genterWrapper").$(byText("Female")).click(); // Пол
            $("#subjectsInput").setValue(userSubject).pressEnter(); // Предметы
            $("#hobbiesWrapper").$(byText("Sports")).click(); // Хобби
            $("#currentAddress").setValue(currentAddresses); // Адрес
            $("#react-select-3-input").setValue(country).pressEnter(); // Страна
            $("#react-select-4-input").setValue(city).pressEnter(); // Город

            $("#uploadPicture").uploadFromClasspath("1.jpg"); //Загрузка картинки

            // Дата рождения
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("September");
            $(".react-datepicker__year-select").selectOption("2008");
            $(".react-datepicker__day--030:not(react-datepicker__day--outside-month)").click();

            $("#submit").click();
        });
        step("Verify form data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

            $(".table-responsive").$(byText("Student Name"))
                    .parent().shouldHave(text(expectedFullName));
            $(".table-responsive").shouldHave(
                    text(firstName),
                    text(lastName),
                    text(userEmail),
                    text(userNumber),
                    text(userSubject),
                    text(userSubject),
                    text(currentAddresses),
                    text(country),
                    text(city),
                    text("30 September,2008"),
                    text("1.jpg"));
        });
    }

}
