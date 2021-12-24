package menu.menucombo;

import components.products.ListProduct;
import components.comboproducts.*;
import lib.IHienThi;
import utils.Display;
import utils.DisplayFormat;
import db.DataBase;

public class DThemCombo implements IHienThi {
    private ListComboProduct danhSachCombo;
	private ListProduct danhSachSanPham;

    public DThemCombo(ListComboProduct list) {
        this.danhSachCombo = list; 
		this.danhSachSanPham = new ListProduct();

		DataBase dbFile = new DataBase("product");
		this.danhSachSanPham.readListDataInDatabase(dbFile.find());
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "THEM COMBO SAN PHAM", '='));
        System.out.printf("\n");
    }

    @Override
    public void xuat() {
		this.danhSachSanPhamVaComboHienCo();
		
        this.nhapDanhSach();
    }

	private void danhSachSanPhamVaComboHienCo() {
		this.danhSachCombo.xuatDanhSachThongTin();
		System.out.printf("\n");
		
		this.danhSachSanPham.xuatDanhSachThongTin();
		System.out.printf("\n");
	}

    private void nhapDanhSach() {
        Display dp = Display.getInstance();

		this.danhSachCombo.nhapDanhSachThongTin();

        DataBase dbFile = new DataBase("comboproduct");
        dbFile.update(danhSachCombo.writeListDataInDatabase());

        System.out.println("Them du lieu thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi((IHienThi)new DMenuCombo());
    }
}
