package menu.menuproduct;

import java.util.Scanner;

import db.DataBase;
import components.products.*;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DSuaProduct implements IHienThi {
	private ListProduct list;

	public DSuaProduct(ListProduct list) {
		this.list = list;
	}
	
	@Override
	public void xuatTitle() {
		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "CHINH SUA THONG TIN THUC AN NHANH", '='));
		System.out.printf("\n\n");
	}

	@Override
	public void xuat() {
		inDanhSachSanPham();

		String input = nhapMaSanPhamCanChinhSua();

		xuLyMaSanPham(input);
	}
	
	private void inDanhSachSanPham() {
        this.list.xuatDanhSachThongTin();
		System.out.printf("\n\n");
	}

	private String nhapMaSanPhamCanChinhSua() {
		System.out.print("Nhap ma san pham can chinh sua thong tin: ");
		String input = Validation.nhapDuLieu(Product.gioiHanDoDaiMaSanPham);
		return input;
	}

	private void xuLyMaSanPham(String input) {
		int indexElement = this.list.indexOf(input);
		Product sanPhamCu = this.list.getElement(indexElement);
		DataBase dbFile = new DataBase("product");
		Display dp = Display.getInstance();

		if(indexElement == -1) {
			throw new InputException("Ma san pham ban nhap khong ton tai, vui long nhap lai!!!");	
		}
		
		Product sanPhamMoi = (sanPhamCu.getClass().getSimpleName().equals("FastFood")) ? new FastFood() : new Drinks();


		while(true) {
			System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "NHAP THONG TIN MOI", '-'));

			try {
				sanPhamMoi.nhap();
				
				//Kiem tra xem dữ liệu nhập có trùng với dữ liệu cũ không
				boolean isEqualsMaSanPham = this.list.contains(sanPhamMoi.getMaSanPham());
				boolean isEqualsTenSanPham = this.list.contains(sanPhamMoi.getTenSanPham());
				if(isEqualsMaSanPham || isEqualsTenSanPham) {
					throw new InputException("Ma san pham hoac ten san pham da co, vui long nhap lai du lieu!!!");
				}

				break;
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// nếu nhập thành công không có lỗi thì chèn dữ liệu mới vào và thoát vòng lặp
		this.list.setElement(indexElement, sanPhamMoi);
		dbFile.update(this.list.writeListDataInDatabase());
		
		// Cập nhật lại các dữ liệu của sản phẩm mới bên file dữ liệu combo sản phẩm
		DataBase dbFileCombo = new DataBase("comboproduct");
		dbFileCombo.findAndUpdate(sanPhamCu.getTenSanPham(), sanPhamMoi.getTenSanPham());

		System.out.println("Chinh sua thong tin thanh cong!!");
		DisplayFormat.dungChuongTrinh();
		// tro ve menu
		dp.hienThi(new DMenuProduct());
	}
}


