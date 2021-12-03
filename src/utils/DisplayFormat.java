package utils;

import java.text.MessageFormat;

public class DisplayFormat {
    public static String inRaChuNamGiua(int length, String str, char c) {
        String charFormat = String.valueOf(c);
        int khoangCachHaiBen = (length - str.length()) / 2;
        String pattern = "%{0}s%s%{0}s";
        String patternDisplay = MessageFormat.format(pattern, khoangCachHaiBen);
        return String.format(patternDisplay, " ", str, " ").replaceAll(" ", charFormat);
    }

    public static String inRaHangCungKyTu(int length, char c) {
        String charFormat = String.valueOf(c); 
        String pattern = "%-{0}s";
        String patternDisplay = MessageFormat.format(pattern, length);
        return String.format(patternDisplay, charFormat).replaceAll(" ", charFormat);
    } 
}
                        

