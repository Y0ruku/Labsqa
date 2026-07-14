package sqa.lab.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test Case Design and Test Results
 * Project: Encryption-Decryption
 * Project ID: Lab3-JUNIT
 * Test Strategy: Gray-box testing
 * Scenario ID: TS001
 */
public class Testlab {

    private final ShiftCipher cipher = new ShiftCipher();
    
    @Test
    public void TC01_shiftSoftwareUppercase() {
        assertEquals("ZVMADHYL", cipher.shift("SOFTWARE", 3));
    } 
    
    @Test
    public void TC02_shiftSoftwareLowercase() {
        assertEquals("zvmadhyl", cipher.shift("software", 3));
    }
    
    @Test
    public void TC03_shiftEasyLowercase() {
        assertEquals("hdvb", cipher.shift("easy", 3));
    }
    
    @Test
    public void TC04_shiftYoruyeduUppercase() {
        assertEquals("BRUXBHGX", cipher.shift("YORUYEDU", 3));
    }
}