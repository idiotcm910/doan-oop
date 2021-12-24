package menu.menucombo;

import java.util.ArrayList;

import components.comboproducts.ListComboProduct;
import db.DataBase;
import lib.IHienThi;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DTimKiemCombo implements IHienThi {
	public DTimKiemCombo() {

	}

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "TIM KIEM COMBO SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		String tenComboSanPhamCanTimKiem = nhapTenComboSanPhamCanTimKiem();

        xuLyTenComboSanPham(tenComboSanPhamCanTimKiem);
    }

    private String nhapTenComboSanPhamCanTimKiem() {
        String str;

        System.out.print("Nhap ten combo san pham can tim kiem: ");
		str = Validation.nhapDuLieu();

        return str;
    }

    private void xuLyTenComboSanPham(String str) {
		DataBase dbFileProduct = new DataBase("comboproduct");
		ArrayList<String> condition = new ArrayList<String>();
		condition.add(str);

		ListComboProduct danhSachTimKiem = new ListComboProduct();
		danhSachTimKiem.readListDataInDatabase(dbFileProduct.findMany(condition));
		
		if(danhSachTimKiem.isEmpty()) {
			System.out.println("Khong tim thay combo san pham!!");
		}
		else {
			danhSachTimKiem.xuatDanhSachThongTin();
		}

		
        Display dp = Display.getInstance();
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuCombo());
    }
}
