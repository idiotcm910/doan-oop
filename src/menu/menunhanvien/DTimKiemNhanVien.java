package menu.menunhanvien;

import java.util.ArrayList;

import components.nhanvien.ListNhanVien;
import db.DataBase;
import lib.IHienThi;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DTimKiemNhanVien implements IHienThi {
	public DTimKiemNhanVien() {

	}

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "TIM KIEM NHAN VIEN", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		String tenNhanVien = nhapTenNhanVien();
        xuLyTenNhanVien(tenNhanVien);
    }

    private String nhapTenNhanVien() {
        String str;
        System.out.print("Nhap ten nhan vien can tim kiem: ");
		str = Validation.nhapDuLieu();
        return str;
    }

    private void xuLyTenNhanVien(String str) {
		DataBase fileNhanVien = new DataBase("nhanvien");
		ArrayList<String> condition = new ArrayList<String>();
		condition.add(str);
		ListNhanVien danhSachTimKiem = new ListNhanVien();
		danhSachTimKiem.readListDataInDatabase(fileNhanVien.findMany(condition));
		if(danhSachTimKiem.isEmpty()) {
			System.out.println("Khong tim thay nhan vien!!");
		}
		else {
			danhSachTimKiem.xuatDanhSachThongTin();
		}
        Display dp = Display.getInstance();
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuNhanVien());
    }
}
