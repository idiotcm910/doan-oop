package products;

import utils.DateIn;
import java.util.Scanner;

import lib.InputException;

abstract class Product
{
    protected String maSanPham;
    protected String tenSanPham;
    protected String ngayNhapThongTin;
    protected int giaSanPham;
    
    public void nhap() {
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");

        this.maSanPham = ip.nextLine().trim();
		if(this.maSanPham.isEmpty()) {
			throw new InputException("Ma san pham khong duoc bo trong!!!");
		}

        System.out.print("Nhap ten san pham: ");
        this.tenSanPham = ip.nextLine().trim();
		if(this.tenSanPham.isEmpty()) {
			throw new InputException("Ten san pham khong duoc bo trong!!!");
		}

        System.out.print("Nhap gia: ");
		try {
        	this.giaSanPham = Integer.parseInt(ip.nextLine());
		}
		catch(Exception ex) {
			throw new InputException("du lieu nhap phai la so!!");
		}

        this.ngayNhapThongTin = DateIn.getCurrentDate();
    }
    
    public String getMaSanPham() {
        return this.maSanPham;
    }
    
    public String getTenSanPham() {
        return this.tenSanPham;
    }
    
    public String getNgayNhapThongTin() {
        return this.ngayNhapThongTin;
    }
    
    public int getGiaSanPham() {
        return this.giaSanPham;
    }
    
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
    
    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }
    
    abstract String xuatThongTin();
    
    abstract boolean contains(String strProperty);
}
