package mode.dsfastfood;

import lib.IHienThi;
import lib.InputException;
import products.ListProduct;
import utils.Display;
import utils.DisplayFormat;

import java.util.Scanner;

import mode.DMenuDSSP;

import db.*;

public class DMenuDSFF implements IHienThi {
    private ListProduct list;
    public DMenuDSFF() {
        list = new ListProduct();
        DataBase dbFile = new DataBase("fastfood");
        list.readListDataInDatabase(dbFile.find());
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "DANH SACH THUC AN NHANH", '='));
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
    	System.out.println(String.format("%-105s", "=").replaceAll(" ", "="));   
	    System.out.printf("%-5s%-8s%-10s%-25s%-11s%-16s%-15s%5s\n",
			" ", "STT", "MaSP", "Ten San Pham", "gia", "hldd", "ngay nhap", " ");
		System.out.println(String.format("%-105s", "-").replaceAll(" ", "-"));   

        this.list.xuatDanhSachThongTin();
        System.out.println(String.format("%-105s", "=").replaceAll(" ", "="));   
    }

    private void danhSachLuaChon() {
        System.out.printf("\n\n");
        System.out.println("1. Them san pham");
        System.out.println("2. Chinh sua thong tin san pham");
        System.out.println("3. Xoa san pham");
        System.out.println("4. Quay lai Menu san pham");
        System.out.println("0. Thoat chuong trinh");
        System.out.print("Nhap lua chon: ");
    }
    
    private int nhapLuaChon() {
        Scanner ip = new Scanner(System.in);
        int choice = 0;
        try {
            choice = Integer.parseInt(ip.nextLine());
        }
        catch(Exception e) {
            throw new InputException("Vui long nhap lua chon bang so!!!");
        }
        return choice;
    }

    private void xuLyLuaChon(int choice) {
        Display dp = Display.getInstance();
        switch(choice) {
			case 1:
                dp.hienThi(new DThemFastFood(this.list));
				break;
            case 2:
                break;
            case 3:
                dp.hienThi(new DXoaFastFood(this.list));
                break;
            case 4:
                dp.hienThi(new DMenuDSSP());
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
