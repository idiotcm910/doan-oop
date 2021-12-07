package mode;

import lib.IHienThi;
import lib.InputException;
import utils.DisplayFormat;
import utils.Validation;
import utils.Display;
import java.util.Scanner;

import mode.menuproduct.DMenuProduct;

public class DMenu implements IHienThi
{
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.getWidthDisplay(), "ME.NU", '='));
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
        System.out.println("0. Thoat");
    }
    
    private int nhapLuaChon() {
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap lua chon: ");
        int choice;
		choice = Validation.nhapDuLieuSo();

        return choice;
    }
    
    private void xuLyLuaChon(int n) {
        Display dp = Display.getInstance();
        switch (n) {
            case 1: 
                dp.hienThi(new DMenuProduct());
                break;
            case 0: 
                Runtime.getRuntime().exit(0);
                break;
            default: 
                throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
        }
    }
}
