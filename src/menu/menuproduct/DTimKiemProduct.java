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
		int choice = this.nhapLuaChon();

		String str = xuLyLuaChon(choice);

        timKiemSanPham(str);
    }

	private int nhapLuaChon() {
		System.out.println("1.Tim kiem theo loai san pham thuc an nhanh");
		System.out.println("2.Tim kiem theo loai san pham thuc uong");
		System.out.println("3.Tim kiem theo ten san pham");

		System.out.print("Nhap lua chon: ");
		int choice = Validation.nhapDuLieuSo();

		return choice;
	}

	// ham nay sẽ xử lý lựa chọn và trả về 1 chuỗi để hàm xuLyTenSanPham tim kiếm sản phẩm
	private String xuLyLuaChon(int choice) {
		String str;

		switch(choice) {
			case 1:
				str = "FF";
				break;
			case 2:
				str = "DS";
				break;
			case 3:
				str = nhapTenSanPhamCanTimKiem();
				break;
			default:
				throw new InputException("Ban nhap sai lua chon, vui long nhap lai!!!");
		}

		return str;
	}

    private String nhapTenSanPhamCanTimKiem() {
        String str;

        System.out.print("Nhap ten san pham can tim kiem: ");
		str = Validation.nhapDuLieu();

        return str;
    }

    private void timKiemSanPham(String str) {
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
