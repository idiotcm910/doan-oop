/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoadon;


import db.DataBase;
import java.util.Scanner;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import hoadon.ListHoaDon;
import hoadon.HoaDon;


/**
 *
 * @author ACER
 */
public class DThemHd implements IHienThi {
    
    public ListHoaDon listhd;
    
    public DThemHd(ListHoaDon listhd) {
        this.listhd = listhd;
    }
    
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(105, "THEM HOA DON", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
        menu();

        int choice = nhapluachon();

        xulyluachon(choice);
    }
    
    
    private void menu() {
       int n;
       System.out.print("Nhap so luong hoa don can them: ");
    }
    
    
    
    private int nhapluachon() {
        Scanner ip = new Scanner(System.in);
        int n = 0;
        try {
            n = Integer.parseInt(ip.nextLine());
        }
        catch(Exception e) {
            throw new InputException("Vui long nhap so!!!");
        }

        if(n <= 0) {
            throw new InputException("So luong hoa don can them phai lon hon 0!!!");
        }

        return n;
    }
    
    
    
    
    
    
    private void xulyluachon(int choice) {
        int n = choice;
        Display dp = Display.getInstance();

        for(int i = 1; i <= n; ++i) {
            HoaDon hdmoi = new HoaDon() {
                @Override
                String xuatthongtin() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                boolean contains(String strProperty) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void xuatTitle() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            System.out.println("====================Nhap thong tin hoa don thu " + i + "==================");
			try {
				hdmoi.nhap();
	            listhd.addHoaDon(hdmoi);
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
				i -= 1;
			}
        }
        
        DataBase dbFile = new DataBase("hoadon");
        dbFile.update(listhd.writeListDataInDatabase());

        Scanner ip = new Scanner(System.in);
        System.out.println("Them du lieu thanh cong!");
        System.out.println("Nhan nut bat ky de tiep tuc.");
        ip.nextLine();
        //dp.hienThi(new DMenuHoaDon());
    }
}

    
