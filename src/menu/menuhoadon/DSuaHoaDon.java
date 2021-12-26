package menu.menuhoadon;

import components.products.ListProduct;
import components.comboproducts.ListComboProduct;
import components.hoadon.HoaDon;
import components.hoadon.ListHoaDon;
import db.DataBase;
import java.util.Scanner;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DSuaHoaDon implements IHienThi {
    public ListHoaDon listhd;
 	private ListProduct listpt;
	private ListComboProduct listcb;


    public DSuaHoaDon(ListHoaDon listhd) {
        this.listhd = listhd;
		DataBase dbFile = new DataBase("product");
		this.listpt = new ListProduct();
		this.listpt.readListDataInDatabase(dbFile.find());
		dbFile = new DataBase("comboproduct");
		this.listcb = new ListComboProduct();
		this.listcb.readListDataInDatabase(dbFile.find());          
    }
    
    @Override
	public void xuatTitle() {
		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "CHINH SUA THONG TIN HOA DON", '='));
		System.out.printf("\n\n");
	}
        
    @Override
	public void xuat() {
		DanhSachHoaDon();

		String a = nhapMaHoaDonCanChinhSua();

		xuLyMaHoaDon(a);
	}  
        
	private void DanhSachHoaDon() {
		this.listpt.xuatDanhSachThongTin();
		System.out.println("\n");
		this.listcb.xuatDanhSachThongTin();
		System.out.println("\n");

        this.listhd.xuatDanhSachThongTin();
		System.out.printf("\n");
	}
        
        private String nhapMaHoaDonCanChinhSua() {
		System.out.print("Nhap ma hoa don can chinh sua thong tin: ");
		String a = Validation.nhapDuLieu();
		return a;
	}
        
        private void xuLyMaHoaDon(String a) {
            int suaHoaDon = this.listhd.indexOf(a);
            HoaDon hdmoi = new HoaDon();
            DataBase dbFile = new DataBase("hoadon");
            Display dp = Display.getInstance();
            
            if(suaHoaDon == -1) {
			throw new InputException("Ma hoa don ban nhap khong ton tai, vui long nhap lai!!!");	
		}
            while(true) {
			System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "NHAP THONG TIN MOI", '-'));
	    try {
                    hdmoi.nhap();
                    boolean isEqualsmaHoaDon = this.listhd.contains(hdmoi.getmaHoaDon());
                    
                    if(isEqualsmaHoaDon) {
					throw new InputException("Ma hoa don da co, vui long nhap lai du lieu!!!");
				}
            
            this.listhd.setElement(suaHoaDon, hdmoi);
				dbFile.update(this.listhd.writeListDataInDatabase());
				break;
	    }
            catch(InputException ex) {
				System.out.println(ex.getMessage());
			}
            }
            System.out.println("Chinh sua thong tin thanh cong!!");
		DisplayFormat.dungChuongTrinh();
	    dp.hienThi(new DMenuHoaDon());
        }
    
}
