/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoadon;

import hoadon.ListHoaDon;
import hoadon.HoaDon;
import java.util.Scanner;

import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;

/**
 *
 * @author ACER
 */
public class DXoaHd implements IHienThi {
    private ListHoaDon listhd;
    
    
    public DXoaHd( ListHoaDon listhd) {
        this.listhd = listhd;

    }
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "XOA HOA DON", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
        this.menuhoadon();

        String maHoaDonCanXoa = this.nhapMaHoaDonCanXoa();

        xuLyMaHoaDon(maHoaDonCanXoa);
    }
    
    
    private void menuhoadon() {
    	System.out.println(String.format("%-105s", "=").replaceAll(" ", "="));   
	    System.out.printf("%-5s%-8s%-10s%-25s%-11s%-16s%-15s%5s\n",
			" ", "maHD", "maHang", "tenHang", "ngayNhapThongTin", "soLuong", "donGia", " ");
		System.out.println(String.format("%-105s", "-").replaceAll(" ", "-"));   

        this.listhd.xuatDanhSachThongTin();
        System.out.println(String.format("%-105s", "=").replaceAll(" ", "="));   
    }
    
    
    private String nhapMaHoaDonCanXoa() {
        Scanner sc = new Scanner(System.in);
        String a;
        System.out.print("Nhap ma san pham can xoa: ");
        a = sc.nextLine();

        return a;
    }
    
    
    private void xuLyMaHoaDon(String str) {
        int indexOfHoaDonRemoved = this.listhd.indexOf(str);

        if(indexOfHoaDonRemoved == -1) {
            throw new InputException("Ma hoa don ban nhap khong tim thay!!!");
        }
        
        this.listhd.remove(indexOfHoaDonRemoved);

        DataBase dbFile = new DataBase("hoadon");
        dbFile.update(this.listhd.writeListDataInDatabase());

        Scanner sc = new Scanner(System.in);
        Display dp = Display.getInstance();
        System.out.println("Xoa hoa don thanh cong!");
        System.out.println("Nhan nut bat ky de tiep tuc.");
        sc.nextLine();
      //  dp.hienThi(new DMenuHoaDon());
    }
}
    
    

