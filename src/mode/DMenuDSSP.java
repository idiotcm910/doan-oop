package mode;
import java.util.Scanner;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;

import mode.dsfastfood.*;

public class DMenuDSSP implements IHienThi {
    public DMenuDSSP() { }

	@Override
	public void xuatTitle() {
        System.out.println(utils.DisplayFormat.inRaChuNamGiua(105, "MENU SAN PHAM", '='));
		System.out.printf("\n\n\n");
	}

	@Override
	public void xuat() {
        this.danhSachLuaChon();

        int choice = this.nhapLuaChon();

        xuLyLuaChon(choice);
	}

    private void danhSachLuaChon() {
    	System.out.println("1. Danh sach thuc an nhanh");
		System.out.println("2. Danh sach thuc uong");
		System.out.println("3. Tro lai menu chinh");
		System.out.println("0. Thoat");
    }

    private int nhapLuaChon() {
        Scanner ip = new Scanner(System.in);
        int choice = 0;
		System.out.printf("\nNhap lua chon: ");

		try {
			choice = Integer.parseInt(ip.nextLine());
		}
		catch(Exception e) {
			throw new InputException("Vui long nhap lua chon la so!!!");
		}

        return choice;
    }

    private void xuLyLuaChon(int choice) {
        Display dp = Display.getInstance();
		switch(choice) {
			case 1:
				dp.hienThi(new DMenuDSFF());
				break;
			case 2:
				break;
			case 3:
				dp.hienThi(new DMenu());
				break;
			case 0:
				// thoat chuong trinh
				Runtime.getRuntime().exit(0);
			default:
				throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
		}
    }
}
