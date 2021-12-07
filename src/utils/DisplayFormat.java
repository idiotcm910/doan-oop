package utils;

import java.text.MessageFormat;

public class DisplayFormat {
	private static int widthDisplay = 131;

	public static int getWidthDisplay() { return widthDisplay; }

    public static String inRaChuNamGiua(int length, String str, char c) {
        String charFormat = String.valueOf(c);
        int khoangCachHaiBen = (length - str.length()) / 2;

		String strLeftAndRight = DisplayFormat.inRaHangCungKyTu(khoangCachHaiBen, c);

		String strCenter = str;
		int lengthStr = str.length();
		if(lengthStr % 2 == 0) {
			strCenter = str.substring(0, lengthStr / 2) + "-" + str.substring(lengthStr / 2);
		}
		
		lengthStr = strCenter.length();
		if(strCenter.charAt(lengthStr / 2 + 1) == ' ') {
			StringBuilder sb = new StringBuilder(strCenter);

            sb.setCharAt(strCenter.length() / 2 + 1, '-');
            strCenter = sb.toString();
		}

		return String.format("%s%s%s", strLeftAndRight, strCenter, strLeftAndRight);
    }

    public static String inRaHangCungKyTu(int length, char c) {
        String charFormat = String.valueOf(c); 
        String pattern = "%-{0}s";
        String patternDisplay = MessageFormat.format(pattern, length);
        return String.format(patternDisplay, charFormat).replaceAll(" ", charFormat);
    } 

	public static String inRaChuHaiBenHang(int length, char c) {
		String charFormat = String.valueOf(c);
		String pattern = "%s%{0}s%s";
		String patternDisplay = MessageFormat.format(pattern, length - 2);
		return String.format(patternDisplay, charFormat, " ", charFormat);
	}
}
                        

