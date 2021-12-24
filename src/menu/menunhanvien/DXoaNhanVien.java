package menu.menunhanvien;

import utils.Display;
import db.DataBase;
import lib.InputException;
import java.util.Scanner;
import utils.DisplayFormat;
import lib.IHienThi;
import components.nhanvien.ListNhanVien;
import utils.Validation;

public class DXoaNhanVien implements IHienThi
{
    public ListNhanVien listnv;
    
    public DXoaNhanVien( ListNhanVien list) {
        this.listnv = list;
    }
    
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "XOA NHAN VIEN", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
        this.danhsachnhanvien();
        this.xoanhanvien();
    }
    
    public void danhsachnhanvien() {
		this.listnv.xuatDanhSachThongTin();
    }
    
    public void xoanhanvien() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String Ma = Validation.nhapDuLieu(4);
        int xoa = this.listnv.indexOf(Ma);
        if (xoa == -1) {
            throw new InputException("Ma nhan vien ban nhap khong tim thay!!!");
        }
        this.listnv.removeElement(xoa);
        DataBase db = new DataBase("nhanvien");
        db.update(this.listnv.writeListDataInDatabase());
        Display dp = Display.getInstance();
        System.out.println("Xoa nhan vien thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi((IHienThi)new DMenuNhanVien());
    }
}
