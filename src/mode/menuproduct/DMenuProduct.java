package mode.menuproduct;

import lib.IHienThi;
import lib.InputException;
import products.ListProduct;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

import java.util.Scanner;

import mode.DMenu;

import db.*;

public class DMenuProduct implements IHienThi {
    private ListProduct list;
    public DMenuProduct() {
        list = new ListProduct();
        DataBase dbFile = new DataBase("product");
        list.readListDataInDatabase(dbFile.find());
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.getWidthDisplay(), "DANH SACH SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
        this.noiDungMenu();

        this.danhSachLuaChon();

        int choice = this.nhapLuaChon();

        this.xuLyLuaChon(choice);
    }

    private void noiDungMenu() {
        this.list.xuatDanhSachThongTin();
		System.out.printf("\n\n");
    }

    private void danhSachLuaChon() {
        System.out.println("1. Them san pham");
        System.out.println("2. Chinh sua thong tin san pham");
        System.out.println("3. Xoa san pham");
        System.out.println("4. Quay lai menu chinh");
        System.out.println("0. Thoat chuong trinh");
        System.out.print("Nhap lua chon: ");
    }
    
    private int nhapLuaChon() {
        Scanner ip = new Scanner(System.in);

        int choice = 0;
		choice = Validation.nhapDuLieuSo();

        return choice;
    }

    private void xuLyLuaChon(int choice) {
        Display dp = Display.getInstance();
        switch(choice) {
			case 1:
                dp.hienThi(new DThemProduct(this.list));
				break;
            case 2:
				dp.hienThi(new DSuaProduct(this.list));
                break;
            case 3:
                dp.hienThi(new DXoaProduct(this.list));
                break;
			case 4:
				dp.hienThi(new DMenu());
				break;
			case 0:
				// thoat chuong trinh
				Runtime.getRuntime().exit(0);
				break;
			default:
				throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
		}
    }
}
