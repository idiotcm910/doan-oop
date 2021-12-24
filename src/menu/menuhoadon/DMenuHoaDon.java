/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoadon;

import db.DataBase;
import java.util.Scanner;
import lib.IHienThi;
import lib.InputException;
import hoadon.ListHoaDon;
import mode.DMenu;
import utils.Display;
import utils.DisplayFormat;
import hoadon.DMenuHoaDon;


/**
 *
 * @author ACER
 */

public class DMenuHoaDon implements IHienThi {
    private ListHoaDon listhd;
    public DMenuHoaDon() {
        listhd = new ListHoaDon();
        DataBase dbFile = new DataBase("hoadon");
        listhd.readListDataInDatabase(dbFile.find());
    }
    
@Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "DANH SACH HOA DON", '='));
        System.out.printf("\n\n");
    }
    
    
    @Override
    public void xuat() {
        this.noidungmenu();

        this.menu();

        int choice = this.nhapluachon();

        this.xulyluachon(choice);
    }
    
    
    private void noidungmenu() {
		System.out.println(DisplayFormat.inRaHangCungKyTu(105, '='));
	    System.out.printf("%-5s%-8s%-10s%-25s%-11s%-16s%-15s%5s\n",
			" ", "maHoaDon", "maHang", "tenHang", "ngayNhapThongTin", "soLuong", "donGia", " ");
		System.out.println(DisplayFormat.inRaHangCungKyTu(105, '='));

        this.listhd.xuatDanhSachThongTin();
		System.out.println(DisplayFormat.inRaHangCungKyTu(105, '='));
    }
    
    
    private void menu() {
        System.out.printf("\n\n");
        System.out.println("1. Them Hoa Don");
        System.out.println("2. Sua Hoa Don");
        System.out.println("3. Xoa Hoa Don");
        System.out.println("4. Quay lai Menu san pham");
        System.out.println("0. Thoat chuong trinh");
        System.out.print("Nhap lua chon: ");
    }
    
    
    
    private int nhapluachon() {
        Scanner ip = new Scanner(System.in);
        int choice = 0;
        try {
            choice = Integer.parseInt(ip.nextLine());
        }
        catch(Exception e) {
            throw new InputException("Vui long nhap lua chon bang so!!!");
        }
        return choice;
    }
    
    
    private void xulyluachon(int choice) {
        Display dp = Display.getInstance();
		switch(choice) {
			case 1:
                dp.hienThi(new DThemHd(this.listhd));
				break;
            case 2:
		dp.hienThi(new DSuaHd(this.listhd));
                break;
            case 3:
                dp.hienThi(new DXoaHd(this.listhd));
                break;
                
           case 4: {
                break;
            }
            case 5: {
                dp.hienThi((IHienThi)new DMenu());
            }
                
			case 0:
				// thoat chuong trinh
				Runtime.getRuntime().exit(0);
			default:
				throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
		}
    }
}

    
        
        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        


