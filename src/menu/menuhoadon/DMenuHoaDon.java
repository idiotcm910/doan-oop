package menu.menuhoadon;

import components.hoadon.ListHoaDon;
import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import menu.DMenu;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DMenuHoaDon implements IHienThi{
    public ListHoaDon listhd;

public DMenuHoaDon() {
        this.listhd = new ListHoaDon();
        DataBase db = new DataBase("hoadon");
        this.listhd.readListDataInDatabase(db.find());
    }


public void xuatTitle(){
    System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "MENU HOA DON", '='));
    System.out.printf("\n");
}


public void xuat(){
		Display dp = Display.getInstance();
		DataBase dbFileProduct = new DataBase("product");
		DataBase dbFileComboProduct = new DataBase("comboproduct");
		if(dbFileProduct.isDataEmpty() || dbFileComboProduct.isDataEmpty()) {
			System.out.println("Chua co du lieu san pham nen khong the them hoa don!!!");
			DisplayFormat.dungChuongTrinh();
			dp.hienThi((IHienThi)new DMenu());
			return;
		}
		if(this.listhd.isEmpty()) {
			System.out.println("Chua co du lieu hoa don vui long them du lieu!!");
			DisplayFormat.dungChuongTrinh();
			dp.hienThi((IHienThi)new DThemHoaDon(this.listhd));
			return;
		}
		this.DanhSachHoaDon();
		this.menuHoaDon();
		int a = this.nhapLuaChon();
		this. xuLyLuaChon(a);
	}

	private void DanhSachHoaDon(){
    	listhd.xuatDanhSachThongTin();
    	System.out.printf("\n\n");
	}


	private void menuHoaDon(){
    	System.out.println("1. Them hoa don");
    	System.out.println("2. Sua hoa don");
    	System.out.println("3. Xoa hoa don");
		System.out.println("4. Tim kiem hoa don");
    	System.out.println("5. Quay lai menu chinh");
    	System.out.println("0. Thoat chuong trinh");
    	System.out.print("Nhap lua chon:");
	}

	private int nhapLuaChon() {
        int a = 0;
		a = Validation.nhapDuLieuSo();
        return a;
    }

	private void xuLyLuaChon(int n) {
        Display dp = Display.getInstance();
        switch (n) {
            case 1: {
                dp.hienThi((IHienThi)new DThemHoaDon(this.listhd));
                break;
            }
            case 2: {
                dp.hienThi((IHienThi)new DSuaHoaDon(this.listhd));
                break;
            }
            case 3: {
                dp.hienThi((IHienThi) new DXoaHoaDon(this.listhd));
                break;
            }
            case 4: {
				dp.hienThi((IHienThi)new DTimKiemHoaDon());
                break;
            }
            case 5: {
                dp.hienThi((IHienThi)new DMenu());
            }
            case 0: {
                Runtime.getRuntime().exit(0);
                break;
            }
            default: {
                throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
            }
        }
    }
}
    



        





