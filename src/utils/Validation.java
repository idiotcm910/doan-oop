package utils;

import lib.InputException;
import java.util.Scanner;

public class Validation {
	private static Scanner ip = new Scanner(System.in);
	
	/**
	 *	Xử lý việc người dùng nhập không phải là số và bỏ trống không nhập
	 */
	public static int nhapDuLieuSo() {
		int input;

		try {
			input = Integer.parseInt(ip.nextLine());
		}
		catch(RuntimeException ex) {
			throw new InputException("Ban vui long nhap du lieu la so!!!");
		}

		return input;
	}

	/**
	 *	xử lý việc người dùng nhập trống dữ liệu
	 * */
	public static String nhapDuLieu() {
		String input = ip.nextLine().trim();
		
		if(input.isEmpty()) {
			throw new InputException("Du lieu nhap khong duoc bo trong!!!");
		}

		return input;
	}

	/**
	 * Xử lý việc người dùng nhập trống dữ liệu và đồ dài dữ liệu vượt quá giới hạn
	 * @param maxLength: độ dài tối đa của chuỗi dữ liệu nhập vào
	 * @return String
	 * */
	public static String nhapDuLieu(int maxLength) {
		String input = Validation.nhapDuLieu();

		if(input.length() > maxLength) {
			throw new InputException("Du lieu nhap khong vuot qua " + maxLength + " ki tu!!!");
		}

		return input;
	}
	/**
	 * Xu ly viec nguoi dung nhap khong phai la so thuc
	 * */
	public static Double nhapDuLieuSoThuc() {
		Double input = 0.0;

		try {
			input = Double.parseDouble(ip.nextLine());
		}
		catch(Exception ex) {
			throw new InputException("Ban vui long nhap du lieu la so!!!");
		}

		if(input < 0) {
			throw new InputException("Ban vui long nhap so lon hon 0!!!");
		}

		return input;
	}

	/**
	 * Xử lý việc người dùng nhập trống dữ liệu và dữ liệu vượt quá phạm vị đối số truyền vào 
	 * @param max: Số giới hạn tối đa
	 * @param min: số giới hạn tối thiểu
	 * @return int
	 * */
	public static int nhapDuLieuSo(int min, int max) {
		int input = Validation.nhapDuLieuSo();

		if(input < min || input > max) {
			throw new InputException("Ban vui long nhap du lieu trong pham vi tu " + min + " den " + max);
		}

		return input;
	}

    /**
	 * Xử lý việc người dùng nhập ngày tháng năm có đúng dạng form dd/mm/yy hay không
	 * @return 
	 * */
    public static String nhapNgayThangNam(){ 
    	String input = Validation.nhapDuLieu();
        if (!input.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			throw new InputException("Ban vui long nhap du lieu theo form dd/mm/yy, vi du: 01/01/2001");
        }
        return input;
    }
}
