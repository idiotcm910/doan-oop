package menu.menuproduct;

import components.products.*;
import lib.IHienThi;
import utils.Display;
import utils.DisplayFormat;
import db.DataBase;

public class DThemProduct implements IHienThi {
    private ListProduct danhSachSanPham;

    public DThemProduct(ListProduct list) {
        this.danhSachSanPham = list; 
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "THEM SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		this.danhSachSanPhamHienCo();
		
		this.nhapDanhSach();;
    }

	private void danhSachSanPhamHienCo() {
		this.danhSachSanPham.xuatDanhSachThongTin();
		System.out.printf("\n");
	}

    private void nhapDanhSach() {
        Display dp = Display.getInstance();

		this.danhSachSanPham.nhapDanhSachThongTin();

        DataBase dbFile = new DataBase("product");
        dbFile.update(danhSachSanPham.writeListDataInDatabase());

        System.out.println("Them du lieu thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi((IHienThi)new DMenuProduct());
    }
}
