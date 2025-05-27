package com.financial.qa;

import java.math.BigInteger;

public class IbanValidator {

    public boolean isValid(String iban) {
        if (iban == null || iban.length() < 15 || iban.length() > 34 || !iban.matches("[A-Z0-9]+")) {
            return false;
        }

        // Move first four chars to end
        String rearranged = iban.substring(4) + iban.substring(0, 4);

        // Convert letters to numbers: A=10, B=11, ..., Z=35
        StringBuilder numericIban = new StringBuilder();
        for (char ch : rearranged.toCharArray()) {
            if (Character.isLetter(ch)) {
                numericIban.append((int) ch - 55); // 'A' is 65 in ASCII, so A=10
            } else {
                numericIban.append(ch);
            }
        }

        // Perform mod-97
        BigInteger ibanNum = new BigInteger(numericIban.toString());
        return ibanNum.mod(BigInteger.valueOf(97)).intValue() == 1;
    }
}
