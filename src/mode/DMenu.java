package mode;

import utils.Display;
import lib.InputException;
import java.util.Scanner;
import utils.DisplayFormat;
import lib.IHienThi;

public class DMenu implements IHienThi
{
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "ME.NU", '='));
        System.out.printf("\n\n", new Object[0]);
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
        try {
            choice = Integer.parseInt(ip.nextLine());
        }
        catch (Exception ex) {
            throw new InputException("Vui long nhap lua chon la so!!!");
        }
        return choice;
    }
    
    private void xuLyLuaChon(int n) {
        Display dp = Display.getInstance();
        switch (n) {
            case 1: 
                dp.hienThi((IHienThi)new DMenuDSSP());
                break;
            case 0: 
                Runtime.getRuntime().exit(0);
                break;
            default: 
                throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
        }
    }
}
