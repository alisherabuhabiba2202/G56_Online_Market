package uz.pdp.g56_online_market.utils;

import java.util.Base64;

public class PasswordEncoder {
    public static boolean matchPassword(String password, String encodedPassword) {
        String s = Base64.getEncoder().encodeToString(password.getBytes());
        return s.equals(encodedPassword);
    }

    public static String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
