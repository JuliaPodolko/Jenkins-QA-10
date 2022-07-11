package tests.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FailedTests {
    @Test
    void test00() {
        assertTrue(false);
    }
    @Test
    void test01() {
        assertTrue(false);
    }
    @Test
    void test02() {
        assertTrue(false);
    }
    @Test
    void test03() {
        assertTrue(false);
    }
    @Test
    void test04() {
        assertTrue(false);
    }
    @Test
    void test05() {
        assertFalse(false);
    }

}
