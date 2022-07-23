package tests.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("simple")
public class SkippedTests {
    @Test
    @Disabled
    void test00() {
        assertTrue(false);
    }
    @Test
    @Disabled
    void test01() {
        assertFalse(false);
    }
    @Test
    @Disabled("some reason")
    void test02() {
        assertFalse(false);
    }
}
