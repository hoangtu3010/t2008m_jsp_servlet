package com.example.hellot2008m.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUntil {
    public static void main(String[] args) {
        checkEmail("hoangtuaaa@gmail.com");
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean checkEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);

        System.out.println(matcher.find());

        return matcher.find();
    }
}
