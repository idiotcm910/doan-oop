
package menu.menunhanvien;

import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import components.nhanvien.*;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DSuaNhanVien implements IHienThi {
    public ListNhanVien listnv;
    
    public DSuaNhanVien( ListNhanVien list) {
        this.listnv = list;
    }
    
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "SUA NHAN VIEN", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
        this.danhsachnhanvien();
        this.suanhanvien();
    }
    
    public void danhsachnhanvien() {
		this.listnv.xuatDanhSachThongTin();
    }
    
    public void suanhanvien() {
        System.out.print("Nhap ma nhan vien can sua: ");
        String Ma = Validation.nhapDuLieu(4);
        NhanVien nvmoi = new NhanVien ();
        DataBase dbFile = new DataBase("nhanvien");
	Display dp = Display.getInstance();
        int sua = this.listnv.indexOf(Ma);
        if (sua == -1) {
            throw new InputException("Ma nhan vien ban nhap khong tim thay!!!");
        }
        while(true) {
			System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "NHAP THONG TIN MOI", '-'));
			try {
                                nvmoi.nhap();
				boolean isEqualsMa = this.listnv.contains(nvmoi.getMa());
				boolean isEqualsCMND= this.listnv.contains(nvmoi.getCMND());
				if(isEqualsMa || isEqualsCMND) {
					throw new InputException("Ma nhan vien hoac CMND da co, vui long nhap lai du lieu!!!");
				}
				this.listnv.setElement(sua, nvmoi);
				dbFile.update(this.listnv.writeListDataInDatabase());
				break;
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
			}
		}
        System.out.println("Chinh sua thong tin thanh cong!!");
		DisplayFormat.dungChuongTrinh();
		dp.hienThi(new DMenuNhanVien());
    }
}
