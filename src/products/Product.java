package products;

import utils.DateIn;
import java.util.Scanner;

abstract class Product
{
    protected String maSanPham;
    protected String tenSanPham;
    protected String ngayNhapThongTin;
    protected int giaSanPham;
    
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");
        this.maSanPham = scanner.nextLine();
        System.out.print("Nhap ten san pham: ");
        this.tenSanPham = scanner.nextLine();
        System.out.print("Nhap gia: ");
        this.giaSanPham = Integer.parseInt(scanner.nextLine());
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
