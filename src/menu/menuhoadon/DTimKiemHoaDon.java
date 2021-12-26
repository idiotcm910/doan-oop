package menu.menuhoadon;

import java.util.ArrayList;

import components.hoadon.ListHoaDon;
import db.DataBase;
import lib.IHienThi;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DTimKiemHoaDon implements IHienThi {
	public DTimKiemHoaDon() {

	}

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "TIM KIEM HOA DON", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		String tenHoaDon = nhapTimKiemHoaDon();
        xuLyHoaDon(tenHoaDon);
    }

    private String nhapTimKiemHoaDon() {
        String str;
        System.out.print("Nhap ten san pham co trong hoa don can tim kiem: ");
		str = Validation.nhapDuLieu();
        return str;
    }

    private void xuLyHoaDon(String str) {
		DataBase file = new DataBase("hoadon");
		ArrayList<String> condition = new ArrayList<String>();
		condition.add(str);
		ListHoaDon danhSachTimKiem = new ListHoaDon();
		danhSachTimKiem.readListDataInDatabase(file.findMany(condition));
		if(danhSachTimKiem.isEmpty()) {
			System.out.println("Khong tim thay hoa don!!");
		}
		else {
			danhSachTimKiem.xuatDanhSachThongTin();
		}
        Display dp = Display.getInstance();
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuHoaDon());
    }
}
