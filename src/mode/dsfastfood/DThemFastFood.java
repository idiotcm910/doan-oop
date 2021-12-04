package mode.dsfastfood;

import java.util.Scanner;
import products.*;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import db.DataBase;

public class DThemFastFood implements IHienThi {
    private ListProduct listEl;

    public DThemFastFood(ListProduct list) {
        this.listEl = list; 
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "THEM THUC AN NHANH", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
        DanhSachLuaChon();

        int choice = nhapLuaChon();

        xuLyLuaChon(choice);
    }

    private void DanhSachLuaChon() {
       int n;
       System.out.print("Nhap so luong san pham thuc an nhanh can them: ");
    }

    private int nhapLuaChon() {
        Scanner ip = new Scanner(System.in);
        int n = 0;
        try {
            n = Integer.parseInt(ip.nextLine());
        }
        catch(Exception e) {
            throw new InputException("Vui long nhap so!!!");
        }

        if(n <= 0) {
            throw new InputException("So luong san pham can them phai lon hon 0!!!");
        }

        return n;
    }

    private void xuLyLuaChon(int choice) {
        int n = choice;
        Display dp = Display.getInstance();

        for(int i = 1; i <= n; ++i) {
            FastFood ff = new FastFood();
            System.out.println("====================Nhap thong tin san pham thu " + i + "==================");
			try {
				ff.nhap();
	            listEl.addElement(ff);
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
				i -= 1;
			}
        }

        DataBase dbFile = new DataBase("fastfood");
        dbFile.update(listEl.writeListDataInDatabase());

        Scanner ip = new Scanner(System.in);
        System.out.println("Them du lieu thanh cong!");
        System.out.println("Nhan nut bat ky de tiep tuc.");
        ip.nextLine();
        dp.hienThi(new DMenuDSFF());
    }
}
