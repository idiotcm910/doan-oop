package menu.menuproduct;

import lib.IHienThi;
import lib.InputException;
import components.products.ListProduct;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

import java.util.Scanner;

import menu.DMenu;

import db.*;

public class DMenuProduct implements IHienThi {
    private ListProduct list;
	private Display dp;
    public DMenuProduct() {
        this.list = new ListProduct();
		this.dp = Display.getInstance();
        DataBase dbFile = new DataBase("product");
        this.list.readListDataInDatabase(dbFile.find());
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "MENU SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		if(this.kiemTraDuLieu()) {
			return;
		}

        this.noiDungMenu();

        this.danhSachLuaChon();

        int choice = this.nhapLuaChon();

        this.xuLyLuaChon(choice);
    }

	private boolean kiemTraDuLieu() {
		boolean isEmptyData = false;

		if(this.list.isEmpty()) {
			isEmptyData = true;
			System.out.println("Hien tai danh sach du lieu dang trong, vui long them du lieu!!");
			DisplayFormat.dungChuongTrinh();
			dp.hienThi((IHienThi)new DThemProduct(this.list));	
		}

		return isEmptyData;
	}

    private void noiDungMenu() {
        this.list.xuatDanhSachThongTin();
		System.out.printf("\n\n");
    }

    private void danhSachLuaChon() {
        System.out.println("1. Them san pham");
        System.out.println("2. Chinh sua thong tin san pham");
        System.out.println("3. Xoa san pham");
        System.out.println("4. Tim kiem san pham");
		System.out.println("5. Quay lai menu chinh");
        System.out.println("0. Thoat chuong trinh");
        System.out.print("Nhap lua chon: ");
    }
    
    private int nhapLuaChon() {
        int choice = 0;
		choice = Validation.nhapDuLieuSo();
        return choice;
    }

    private void xuLyLuaChon(int choice) {
		IHienThi iHT = null;

        switch(choice) {
			case 1:
                iHT = (IHienThi)new DThemProduct(this.list);
				break;
            case 2:
				iHT = (IHienThi)new DSuaProduct(this.list);
                break;
            case 3:
                iHT = (IHienThi)new DXoaProduct(this.list);
                break;
			case 4:
				iHT = (IHienThi)new DTimKiemProduct();
				break;
			case 5:
				iHT = (IHienThi)new DMenu();
				break;
			case 0:
				// thoat chuong trinh
				Runtime.getRuntime().exit(0);
				break;
			default:
				throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
		}

		dp.hienThi(iHT);
    }
}
