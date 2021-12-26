package menu.menunhanvien;

import db.DataBase;
import utils.Display;
import utils.DisplayFormat;
import lib.IHienThi;
import components.nhanvien.ListNhanVien;

public class DThemNhanVien implements IHienThi
{
    public ListNhanVien listnv;
    
    public DThemNhanVien(ListNhanVien list) {
        this.listnv = list;
    }
    
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "THEM NHAN VIEN", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
		this.listnv.xuatDanhSachThongTin();
		System.out.println("\n");
		this.nhapDanhSach();
    }
    
    
    public void nhapDanhSach() {
        Display dp = Display.getInstance();
		this.listnv.nhapDanhSachThongTin();
        DataBase db = new DataBase("nhanvien");
        db.update(this.listnv.writeListDataInDatabase());
        System.out.println("Them du lieu thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi((IHienThi)new DMenuNhanVien());
    }
}
