package menu;

import lib.IHienThi;
import lib.InputException;
import utils.DisplayFormat;
import utils.Validation;
import utils.Display;
import java.util.Scanner;

import menu.menucombo.DMenuCombo;
import menu.menuhoadon.DMenuHoaDon;
import menu.menuproduct.DMenuProduct;
import menu.menunhanvien.DMenuNhanVien;

public class DMenu implements IHienThi
{
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "ME.NU", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
        this.danhSachLuaChon();

        int choice = nhapLuaChon();

        xuLyLuaChon(choice);
    }
    
    private void danhSachLuaChon() {
        System.out.println("1. Xem danh sach san pham hien co trong cua hang");
		System.out.println("2. Xem danh sach combo san pham hien co trong cua hang");
		System.out.println("3. Xem danh sach nhan vien dang lam viec trong cua hang");
		System.out.println("4. Xem danh sach hoa don trong cua hang");
        System.out.println("0. Thoat");
    }
    
    private int nhapLuaChon() {
        System.out.print("Nhap lua chon: ");

        int choice;
		choice = Validation.nhapDuLieuSo();

        return choice;
    }
    
    private void xuLyLuaChon(int n) {
        Display dp = Display.getInstance();

		IHienThi iHT = null;
        switch (n) {
            case 1: 
                iHT = (IHienThi)new DMenuProduct();
                break;
			case 2:
				iHT = (IHienThi)new DMenuCombo();
				break;
			case 3:
				iHT = (IHienThi)new DMenuNhanVien();
				break;
			case 4:
				iHT = (IHienThi)new DMenuHoaDon();
				break;
            case 0: 
                Runtime.getRuntime().exit(0);
                break;
            default: 
                throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
        }

		dp.hienThi(iHT);
    }
}
