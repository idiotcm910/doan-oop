package menu.menuhoadon;

import components.hoadon.ListHoaDon;
import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DXoaHoaDon implements IHienThi{
    private ListHoaDon listhd;

    public DXoaHoaDon(ListHoaDon listhd) {
        this.listhd=listhd;
    }
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "XOA HOA DON", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
        this.danhSachHoaDon();

        String maHoaDon = this.nhapMaHoaDon();

        xuLyMaHoaDon(maHoaDon);
    }
    
    
    private void danhSachHoaDon() {
        this.listhd.xuatDanhSachThongTin();
		System.out.print("\n");
    }
    
    private String nhapMaHoaDon() {
        String a;
        System.out.print("Nhap ma hoa don can xoa: ");
		a = Validation.nhapDuLieu();
        return a;
    }
    
    
    private void xuLyMaHoaDon(String a) {
        int indexOfHoaDon = this.listhd.indexOf(a);
        
        if(indexOfHoaDon == -1) {
            throw new InputException("Ma hoa don ban nhap khong tim thay!!!");
        }
        this.listhd.removeElement(indexOfHoaDon);
        DataBase dbFile = new DataBase("hoadon");
        dbFile.update(this.listhd.writeListDataInDatabase());

        Display dp = Display.getInstance();
        System.out.println("Xoa hoa don thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuHoaDon());
    }
    
    }
    

