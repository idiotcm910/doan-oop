package products;

import utils.DateIn;
import utils.Validation;
import java.util.Scanner;

import lib.InputException;

abstract public class Product
{
    protected String maSanPham;
    protected String tenSanPham;
    protected String ngayNhapThongTin;
    protected int giaSanPham;
    
    public void nhap() {
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");

        this.maSanPham = Validation.nhapDulieu();

		// Kiểm trả mã sản phẩm chỉ tối đa 4 ký tự
		if(this.maSanPham.length() > 4) {
			throw new InputException("Ma san pham chi duoc nhap toi da 4 ki tu!!!");
		}

        System.out.print("Nhap ten san pham: ");
		this.tenSanPham = Validation.nhapDulieu();

        System.out.print("Nhap gia: ");
		this.giaSanPham = Validation.nhapDuLieuSo();

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
