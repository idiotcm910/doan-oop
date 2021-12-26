package menu.menunhanvien;

import menu.DMenu;
import utils.Display;
import lib.InputException;
import utils.DisplayFormat;
import utils.Validation;
import db.DataBase;
import lib.IHienThi;
import components.nhanvien.*;

public class DMenuNhanVien implements IHienThi
{
    public ListNhanVien listnv;
    
    public DMenuNhanVien() {
        this.listnv = new ListNhanVien();
        DataBase db = new DataBase("nhanvien");
        this.listnv.readListDataInDatabase(db.find());
    }
    
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "MENU NHAN VIEN", '='));
        System.out.printf("\n\n\n");
    }
    
    public void xuat() {
		if(this.listnv.isEmpty()) {
			System.out.println("Chua co du lieu nhan vien, vui long them du lieu!!");
			Display dp = Display.getInstance();
			DisplayFormat.dungChuongTrinh();
			dp.hienThi((IHienThi)new DThemNhanVien(this.listnv));
			return;
		}
        this.noiDungMenu();
        this.menu();
        int n = this.nhapLuaChon();
        this.xuLyLuaChon(n);
    }
    
    public void menu() {
        System.out.println("1. Them nhan vien");
        System.out.println("2. Xoa nhan vien");
        System.out.println("3. Sua thong tin nhan vien");
        System.out.println("4. Tim kiem thong tin nhan vien");
        System.out.println("5. Tro lai menu chinh");
        System.out.println("0. Thoat");
    }
    
    public void noiDungMenu() {
		this.listnv.xuatDanhSachThongTin();
		System.out.println("\n\n");
    }
    
    public int nhapLuaChon() {
        int n = 0;
        System.out.print("Nhap lua chon: ");
		n = Validation.nhapDuLieuSo();
        return n;
    }
    
    public void xuLyLuaChon(int n) {
        Display dp = Display.getInstance();
        switch (n) {
            case 1: {
                dp.hienThi((IHienThi)new DThemNhanVien(this.listnv));
                break;
            }
            case 2: {
                dp.hienThi((IHienThi)new DXoaNhanVien(this.listnv));
                break;
            }
            case 3: {
                dp.hienThi((IHienThi)new DSuaNhanVien(this.listnv));
                break;
            }
            case 4: {
				dp.hienThi((IHienThi)new DTimKiemNhanVien());
                break;
            }
            case 5: {
                dp.hienThi((IHienThi)new DMenu());
				break;
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
