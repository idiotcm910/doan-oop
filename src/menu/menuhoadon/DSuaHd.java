/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoadon;


import db.DataBase;
import lib.IHienThi;
import hoadon.ListHoaDon;
import hoadon.HoaDon;
import java.util.Scanner;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;


/**
 *
 * @author ACER
 */
    public class DSuaHd implements IHienThi {
    public ListHoaDon listhd;
    
    

    public DSuaHd(ListHoaDon list) {
		this.listhd = listhd;
	}

@Override
	public void xuatTitle() {
		System.out.println(DisplayFormat.inRaChuNamGiua(105, "SUA HOA DON", '='));
		System.out.printf("\n\n");
	}
        
        
        @Override
	public void xuat() {
		inmenuhoadon();

		String input = nhapMaHoaDonCanChinhSua();

		xuLyMaHoaDon(input);
	}
        
        
        
        
        
        


public void inmenuhoadon() {
        System.out.println(DisplayFormat.inRaHangCungKyTu(DisplayFormat.getWidthDisplay(), '='));
        System.out.printf("%5s%-8s%-20s%-10s%-13s%-13s%-13s%-7s%-13s%-10s%-5s%-5s\n", " maHoaDon ", "maHang", "tenHang", "ngayNhapThongTin", "soLuong", "donGia"," ");
        System.out.println(DisplayFormat.inRaHangCungKyTu(DisplayFormat.getWidthDisplay(), '='));
        this.listhd.xuatDanhSachThongTin();
        System.out.println(DisplayFormat.inRaHangCungKyTu(DisplayFormat.getWidthDisplay(), '='));
    }

private String nhapMaHoaDonCanChinhSua() {
		Scanner ip = new Scanner(System.in);

		System.out.print("Nhap ma hoa don can chinh sua thong tin: ");
		String input = ip.nextLine().trim();
		return input;
	}



private void xuLyMaHoaDon(String input) {
		int indexHoaDon = this.listhd.indexOf(input);
		HoaDon hoaDonCu = (HoaDon)this.listhd.getHoaDon(indexHoaDon);
		DataBase dbFile = new DataBase("hoadon");
		Display dp = Display.getInstance();

		if(indexHoaDon == -1) {
			throw new InputException("Ma hoa don ban nhap khong ton tai, vui long nhap lai!!!");	
		}

		System.out.println(DisplayFormat.inRaChuNamGiua(105, "NHAP THONG TIN MOI", '-'));
		while(true) {
			HoaDon hd = new HoaDon() {
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
			try {
				hd.nhap();
				
				//Kiem tra xem dữ liệu nhập có trùng với dữ liệu cũ không
				if(hoaDonCu.equals(hd) || this.listhd.contains(hd)) {
					throw new InputException("Thong tin hoa don moi bi trung voi san pham cu, vui long nhap lai!!!");
				}

				// nếu nhập thành công không có lỗi thì chèn dữ liệu mới vào và thoát vòng lặp
				this.listhd.setHoaDon(indexHoaDon, hd);
				dbFile.update(this.listhd.writeListDataInDatabase());
				break;
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("Chinh sua thong tin thanh cong!!");
		System.out.println("Nhap nut bat ky de quay tro lai");
		(new Scanner(System.in)).nextLine();
		// tro ve menu
                //dp.hienThi(new DMenuHoaDon());
	}
}







    

