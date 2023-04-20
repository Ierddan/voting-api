package com.ierddan.votingapi.util;

public class AssociateUtils {

    public static boolean validateCpf(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$|^\\d{11}$";
        if (!cpf.matches(regex)) {
            return false;
        }
        cpf = cpf.replaceAll("\\D", "");
        int[] numbers = new int[11];
        for (int i = 0; i < 11; i++) {
            numbers[i] = Character.getNumericValue(cpf.charAt(i));
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += numbers[i] * (10 - i);
        }
        int resto = sum % 11;
        int verifyingDigit1 = resto < 2 ? 0 : 11 - resto;
        if (numbers[9] != verifyingDigit1) {
            return false;
        }
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += numbers[i] * (11 - i);
        }
        resto = sum % 11;
        int verifyingDigit2 = resto < 2 ? 0 : 11 - resto;
        return numbers[10] == verifyingDigit2;
    }

    public static boolean validateCep(String cep) {
        String regex = "\\d{5}-\\d{3}";
        return cep.matches(regex);
    }
}
