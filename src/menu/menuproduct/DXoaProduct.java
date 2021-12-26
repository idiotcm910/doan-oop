package menu.menuproduct;

import components.products.ListProduct;
import components.products.Product;

import java.util.Scanner;

import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DXoaProduct implements IHienThi {
    private ListProduct list;
    public DXoaProduct(ListProduct list) {
        this.list = list;
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "XOA SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
        this.danhSachSanPham();

        String maSanPhamCanXoa = this.nhapMaSanPhamCanXoa();

        xuLyMaSanPham(maSanPhamCanXoa);
    }

    private void danhSachSanPham() {
        this.list.xuatDanhSachThongTin();
		System.out.print("\n");
    }

    private String nhapMaSanPhamCanXoa() {
        String str;
        System.out.print("Nhap ma san pham can xoa: ");
		str = Validation.nhapDuLieu(Product.gioiHanDoDaiMaSanPham);
        return str;
    }

    private void xuLyMaSanPham(String str) {
        int indexOfProductRemoved = this.list.indexOf(str);

        if(indexOfProductRemoved == -1) {
            throw new InputException("Ma san pham ban nhap khong tim thay!!!");
        }

 		String tenSanPham = this.list.getElement(indexOfProductRemoved).getTenSanPham();
       
        this.list.removeElement(indexOfProductRemoved);

        DataBase dbFile = new DataBase("product");
        dbFile.update(this.list.writeListDataInDatabase());

		// Xóa các dữ liệu của sản phẩm này bên file dữ liệu combo sản phẩm
		DataBase dbFileCombo = new DataBase("comboproduct");
		dbFileCombo.findAndDelete(tenSanPham);
		
        Display dp = Display.getInstance();
        System.out.println("Xoa san pham thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuProduct());
    }
}
