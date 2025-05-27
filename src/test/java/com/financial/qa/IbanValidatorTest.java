package com.financial.qa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class IbanValidatorTest {

    private final IbanValidator validator = new IbanValidator();

    @ParameterizedTest
    @ValueSource(strings = {
        "GB82WEST12345698765432", // Valid GB
        "DE89370400440532013000", // Valid DE
        "FR7630006000011234567890189" // Valid FR
    })
    public void testValidIbans(String iban) {
        assertTrue(validator.isValid(iban));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "GB82WEST123", // too short
        "GB82WEST1234569@#65432", // invalid characters
        "", // empty
        "    ", // spaces
        "1234567890123456789012345678901234567890" // too long
    })
    public void testInvalidIbans(String iban) {
        assertFalse(validator.isValid(iban));
    }

    @Test
    public void testNullIban() {
        assertFalse(validator.isValid(null));
    }

    @Test
    public void testInvalidMod97Checksum() {
        // Valid format but wrong checksum
        String iban = "GB82WEST12345698765431"; // wrong check digits
        assertFalse(validator.isValid(iban));
    }
}