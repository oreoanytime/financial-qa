package com.financial.qa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;


public class IbanValidatorTest {
    
    private final IbanValidator validator = new IbanValidator();

    // Positive Test (Happy path)
    @Test
    public void testValidIban() {
        String iban = "GB82WEST12345698765432";
        assertTrue(validator.isValid(iban));
    }

    // Negative Tests
    @Test
    public void testInvalidIbanLength() {
        String iban = "GB82WEST123";
        assertFalse(validator.isValid(iban));
    }

    // Check Bad Input
    @Test
    public void testInvalidCharacters() {
        String iban = "GB82WEST1234569@#65432";
        assertFalse(validator.isValid(iban));
    }

    // Check Null
    @Test
    public void testNullIban() {
        assertFalse(validator.isValid(null));
    }

    // Check Empty String
    @Test
    public void testEmptyIban() {
        assertFalse(validator.isValid(""));
    }
}

