package menu.menuproduct;

import java.util.ArrayList;
import java.util.Scanner;

import components.products.ListProduct;
import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DTimKiemProduct implements IHienThi {
	public DTimKiemProduct() {

	}

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "TIM KIEM SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		String tenSanPhamCanTimKiem = nhapTenSanPhamCanTimKiem();

        xuLyTenSanPham(tenSanPhamCanTimKiem);
    }

    private String nhapTenSanPhamCanTimKiem() {
        String str;

        System.out.print("Nhap ten san pham can tim kiem: ");
		str = Validation.nhapDuLieu();

        return str;
    }

    private void xuLyTenSanPham(String str) {
		DataBase dbFileProduct = new DataBase("product");
		ArrayList<String> condition = new ArrayList<String>();
		condition.add(str);

		ListProduct danhSachTimKiem = new ListProduct();
		danhSachTimKiem.readListDataInDatabase(dbFileProduct.findMany(condition));
		
		if(danhSachTimKiem.isEmpty()) {
			System.out.println("Khong tim thay san pham!!");
		}
		else {
			danhSachTimKiem.xuatDanhSachThongTin();
		}

		
        Display dp = Display.getInstance();
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuProduct());
    }
}
