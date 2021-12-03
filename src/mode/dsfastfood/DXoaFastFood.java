package mode.dsfastfood;

import products.ListProduct;
import products.FastFood;
import java.util.Scanner;

import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;

public class DXoaFastFood implements IHienThi {
    private ListProduct list;
    public DXoaFastFood(ListProduct list) {
        this.list = list;
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "XOA SAN PHAM THUC AN NHANH", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
        this.danhSachThucAnNhanh();

        String maSanPhamCanXoa = this.nhapMaSanPhamCanXoa();

        xuLyMaSanPham(maSanPhamCanXoa);
    }

    private void danhSachThucAnNhanh() {
    	System.out.println(String.format("%-105s", "=").replaceAll(" ", "="));   
	    System.out.printf("%-5s%-8s%-10s%-25s%-11s%-16s%-15s%5s\n",
			" ", "STT", "MaSP", "Ten San Pham", "gia", "hldd", "ngay nhap", " ");
		System.out.println(String.format("%-105s", "-").replaceAll(" ", "-"));   

        this.list.xuatDanhSachThongTin();
        System.out.println(String.format("%-105s", "=").replaceAll(" ", "="));   
    }

    private String nhapMaSanPhamCanXoa() {
        Scanner ip = new Scanner(System.in);
        String str;
        System.out.print("Nhap ma san pham can xoa: ");
        str = ip.nextLine();

        return str;
    }

    private void xuLyMaSanPham(String str) {
        FastFood ffRemove = (FastFood)this.list.findElement(str); 

        if(ffRemove == null) {
            throw new InputException("Ma san pham ban nhap khong tim thay!!!");
        }
        
        this.list.remove(ffRemove);

        DataBase dbFile = new DataBase("fastfood");
        dbFile.update(this.list.writeListDataInDatabase());

        Scanner ip = new Scanner(System.in);
        Display dp = Display.getInstance();
        System.out.println("Xoa san pham thanh cong!");
        System.out.println("Nhan nut bat ky de tiep tuc.");
        ip.nextLine();
        dp.hienThi(new DMenuDSFF());
    }
}
