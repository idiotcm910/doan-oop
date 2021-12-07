package utils;

import lib.InputException;
import java.util.Scanner;

public class Validation {
	private static Scanner ip = new Scanner(System.in);
	
	/**
	 *	Xử lý việc người dùng nhập cho trường dữ liệu số là chữ
	 */
	public static int nhapDuLieuSo() {
		int choice;

		try {
			choice = Integer.parseInt(ip.nextLine());
		}
		catch(RuntimeException ex) {
			throw new InputException("Ban vui long nhap du lieu la so!!!");
		}

		return choice;
	}

	/**
	 *	xử lý việc người dùng nhập trống dữ liệu
	 * */
	public static String nhapDulieu() {
		String input = ip.nextLine().trim();
		
		if(input.isEmpty()) {
			throw new InputException("Du lieu nhap khong duoc bo trong!!!");
		}

		return input;
	}
}
