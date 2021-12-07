package mode.menuproduct;

import products.ListProduct;
import products.FastFood;
import java.util.Scanner;

import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;

public class DXoaProduct implements IHienThi {
    private ListProduct list;
    public DXoaProduct(ListProduct list) {
        this.list = list;
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.getWidthDisplay(), "XOA SAN PHAM", '='));
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
		System.out.print("\n\n");
    }

    private String nhapMaSanPhamCanXoa() {
        Scanner ip = new Scanner(System.in);
        String str;
        System.out.print("Nhap ma san pham can xoa: ");
        str = ip.nextLine();

        return str;
    }

    private void xuLyMaSanPham(String str) {
        int indexOfProductRemoved = this.list.indexOf(str);

        if(indexOfProductRemoved == -1) {
            throw new InputException("Ma san pham ban nhap khong tim thay!!!");
        }
        
        this.list.remove(indexOfProductRemoved);

        DataBase dbFile = new DataBase("product");
        dbFile.update(this.list.writeListDataInDatabase());

        Scanner ip = new Scanner(System.in);
        Display dp = Display.getInstance();
        System.out.println("Xoa san pham thanh cong!");
        System.out.println("Nhan nut bat ky de tiep tuc.");
        ip.nextLine();
        dp.hienThi(new DMenuProduct());
    }
}
