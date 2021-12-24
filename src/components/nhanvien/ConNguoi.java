package components.nhanvien; 

import java.util.ArrayList;
import java.util.Scanner;
import utils.Validation; 
abstract class ConNguoi
{
    protected String HoTen;
    protected String GioiTinh;
    protected String NgaySinh;
    protected String SoDienThoai;
    protected String CMND;
    
    public ConNguoi() {
    }
    
    public ConNguoi(String HoTen, String GioiTinh, String NgaySinh, String SoDienThoai, String CMND) {
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.SoDienThoai = SoDienThoai;
        this.CMND = CMND;
    }
    
    public String getHoTen() {
        return this.HoTen;
    }
    
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    
    public String getGioiTinh() {
        return this.GioiTinh;
    }
    
    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }
    
    public String getNgaySinh() {
        return this.NgaySinh;
    }
    
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    
    public String getSoDienThoai() {
        return this.SoDienThoai;
    }
    
    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    
    public String getCMND() {
        return this.CMND;
    }
    
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }
    
    public void nhapthongtin() {
        System.out.print("Nhap ho ten: ");
        this.HoTen = Validation.nhapDuLieu();
        System.out.print("Nhap gioi tinh: ");
        this.GioiTinh = Validation.nhapDuLieu();
        System.out.print("Nhap ngay sinh: ");
        this.NgaySinh = Validation.nhapDuLieu();
        System.out.print("Nhap so dien thoai: ");
        this.SoDienThoai = Validation.nhapDuLieu();
        System.out.print("Nhap so chung minh nhan dan: ");
        this.CMND = Validation.nhapDuLieu();
    }
    
    abstract ArrayList<String> xuatThongTin(); 
    
    abstract boolean contains(String strProperty);
}
