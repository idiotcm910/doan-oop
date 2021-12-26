package utils;

import java.text.MessageFormat;
import java.util.Scanner;
public class DisplayFormat {
	public final static int widthDisplay = 158;

	/* 
	 * in ra chữ nằm giữa hàng
	 * ví dụ: ---------------------Son------------------
	 * */
    public static String inRaChuNamGiua(int length, String str, char c) {
        int khoangCachHaiBen = (length - str.length()) / 2;

		String strLeft = DisplayFormat.inRaHangCungKyTu(khoangCachHaiBen, c);
		String strRight = strLeft;

		if(strLeft.length() + strRight.length() + str.length() != length) {
			strRight += c;
		}

		return String.format("%s%s%s", strLeft, str, strRight);
    }

	/* 
	 * in ra một hàng cùng kí tụ
	 * ví dụ: ============================================
	 * */
    public static String inRaHangCungKyTu(int length, char c) {
        String charFormat = String.valueOf(c); 
        String pattern = "%-{0}s";
        String patternDisplay = MessageFormat.format(pattern, length);
        return String.format(patternDisplay, charFormat).replaceAll(" ", charFormat);
    } 

	/* 
	 * in ra 1 hàng có chữ nằm ở cuối hàng và đầu hàng
	 * ví dụ: |                               |
	 * */
	public static String inRaChuHaiBenHang(int length, char c) {
		String charFormat = String.valueOf(c);
		String pattern = "%s%{0}s%s";
		String patternDisplay = MessageFormat.format(pattern, length - 2);
		return String.format(patternDisplay, charFormat, " ", charFormat);
	}

	/**
	 *	Hàm dừng chương trình đang thực thi và yêu cầu người
	 *	dùng nhấn nút bất ký để tiếp tục chương trình
	 * */
	public static void dungChuongTrinh() {
		Scanner ip = new Scanner(System.in);
        System.out.println("Nhan nut bat ky de tiep tuc.");
        ip.nextLine();	
	}
}
                        

