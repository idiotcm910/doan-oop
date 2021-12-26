package menu.menucombo;

import java.util.Scanner;

import components.comboproducts.ListComboProduct;
import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import menu.DMenu;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DMenuCombo implements IHienThi {
	private ListComboProduct danhSachCombo;
	private Scanner ip;
	private Display dp;

	public DMenuCombo() {
		DataBase dbFile = new DataBase("comboproduct");
		this.danhSachCombo = new ListComboProduct();
		this.danhSachCombo.readListDataInDatabase(dbFile.find());
		this.ip = new Scanner(System.in);
		this.dp = Display.getInstance();
	}

	@Override
	public void xuatTitle() {
		int widthDisplay = DisplayFormat.widthDisplay;
		System.out.println(DisplayFormat.inRaChuNamGiua(widthDisplay, "MENU COMBO SAN PHAM", '='));
		System.out.printf("\n");
	}

	@Override
	public void xuat() {
		if(this.kiemTraDuLieu()) {
			return;
		}

		this.inRaDanhSachComBo();	

		this.danhSachLuaChon();

		int choice = this.nhapLuaChon();

		this.xuLyLuaChon(choice);
	}
	
	private boolean kiemTraDuLieu() {
		boolean isEmptyData = false;
		IHienThi menuDieuHuong = null;

		// kiểm tra danh sách sản phẩm, nếu danh sách không có dữ liệu thi sẽ điều hướng trở về menu chính
		DataBase dbFile = new DataBase("product");
		if(dbFile.isDataEmpty()) {
			System.out.println("Hien tai danh sach san pham dang trong, nen khong the nhap du lieu cho danh sach combo san pham!!");
			isEmptyData = true;
			menuDieuHuong = new DMenu();
		}
		// Nếu danh sách combo đang trống sẽ điều hướng sang menu thêm combo để thêm dữ liệu
		if(this.danhSachCombo.isEmpty() && isEmptyData != true) {
			System.out.println("Hien tai danh sach du lieu dang trong, vui long them du lieu!!");
			isEmptyData = true;
			menuDieuHuong = new DThemCombo(this.danhSachCombo);
		}
		
		if(isEmptyData == true) {
			DisplayFormat.dungChuongTrinh();
			dp.hienThi((IHienThi)menuDieuHuong);
			return true;
		}

		return false;
	} 

	private void inRaDanhSachComBo() {
		danhSachCombo.xuatDanhSachThongTin();
		System.out.printf("\n\n");
	}

    private void danhSachLuaChon() {
        System.out.println("1. Them combo san pham");
        System.out.println("2. Chinh sua thong tin combo san pham");
        System.out.println("3. Xoa combo san pham");
		System.out.println("4. Tim kiem combo san pham");
        System.out.println("5. Quay lai menu chinh");
        System.out.println("0. Thoat chuong trinh");
        System.out.print("Nhap lua chon: ");
    }
    
    private int nhapLuaChon() {
        int choice = 0;
		choice = Validation.nhapDuLieuSo();
        return choice;
    }

    private void xuLyLuaChon(int choice) {
        Display dp = Display.getInstance();
		IHienThi iHT = null;

        switch(choice) {
			case 1:
                iHT = (IHienThi)new DThemCombo(this.danhSachCombo);
				break;
            case 2:
				iHT = (IHienThi)new DSuaCombo(this.danhSachCombo); 
                break;
            case 3:
                iHT = (IHienThi)new DXoaCombo(this.danhSachCombo);
                break;
			case 4:
				iHT = (IHienThi)new DTimKiemCombo();
				break;
			case 5:
				iHT = (IHienThi)new DMenu();
				break;
			case 0:
				// thoat chuong trinh
				Runtime.getRuntime().exit(0);
				break;
			default:
				throw new InputException("Lua chon ban nhap khong dung, vui long nhap lai!!!");
		}

		dp.hienThi(iHT);
    }
}
