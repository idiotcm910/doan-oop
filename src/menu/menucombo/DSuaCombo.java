package menu.menucombo;

import db.DataBase;
import components.comboproducts.ComboProduct;
import components.comboproducts.ListComboProduct;
import components.products.*;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DSuaCombo implements IHienThi {
	private ListComboProduct danhSachCombo;
	private ListProduct danhSachSanPham;

	public DSuaCombo(ListComboProduct list) {
		this.danhSachCombo = list;
		this.danhSachSanPham = new ListProduct();
		DataBase dbFileProduct = new DataBase("product");
		this.danhSachSanPham.readListDataInDatabase(dbFileProduct.find());
	}
	
	@Override
	public void xuatTitle() {
		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "CHINH SUA THONG TIN COMBO SAN PHAM", '='));
		System.out.printf("\n\n");
	}

	@Override
	public void xuat() {
		inDanhSachSanPham();

		String input = nhapMaSanPhamCanChinhSua();

		xuLyMaSanPham(input);
	}
	
	private void inDanhSachSanPham() {
        this.danhSachCombo.xuatDanhSachThongTin();
		System.out.printf("\n");
		this.danhSachSanPham.xuatDanhSachThongTin();
		System.out.printf("\n");	
	}

	private String nhapMaSanPhamCanChinhSua() {
		System.out.print("Nhap ma san pham can chinh sua thong tin: ");
		String input = Validation.nhapDuLieu(ComboProduct.gioihanDoDaiMaCombo);
		return input;
	}

	private void xuLyMaSanPham(String input) {
		int indexElement = this.danhSachCombo.indexOf(input);
		ComboProduct comboSanPhamMoi = new ComboProduct();
		DataBase dbFile = new DataBase("comboproduct");
		Display dp = Display.getInstance();

		if(indexElement == -1) {
			throw new InputException("Ma combo san pham ban nhap khong ton tai, vui long nhap lai!!!");	
		}
		
		while(true) {
			System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "NHAP THONG TIN MOI", '-'));

			try {
				comboSanPhamMoi.nhapThongTin();
				
				//Kiem tra xem dữ liệu nhập có trùng với dữ liệu cũ không
				boolean isEqualsMaComboSanPham = this.danhSachCombo.contains(comboSanPhamMoi.getMaComboSanPham());
				boolean isEqualsTenComboSanPham = this.danhSachCombo.contains(comboSanPhamMoi.getTenComboSanPham());
				if(isEqualsMaComboSanPham || isEqualsTenComboSanPham) {
					throw new InputException("Ma combo san pham hoac ten combo san pham da co, vui long nhap lai du lieu!!!");
				}

				// nếu nhập thành công không có lỗi thì chèn dữ liệu mới vào và thoát vòng lặp
				this.danhSachCombo.setElement(indexElement, comboSanPhamMoi);
				dbFile.update(this.danhSachCombo.writeListDataInDatabase());
				break;
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("Chinh sua thong tin thanh cong!!");
		DisplayFormat.dungChuongTrinh();
		// tro ve menu
		dp.hienThi(new DMenuCombo());
	}
}


