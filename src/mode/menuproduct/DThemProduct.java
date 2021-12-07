package mode.menuproduct;

import java.util.Scanner;
import products.*;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;
import db.DataBase;

public class DThemProduct implements IHienThi {
    private ListProduct listEl;

    public DThemProduct(ListProduct list) {
        this.listEl = list; 
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.getWidthDisplay(), "THEM SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
		danhSachSanPhamHienCo();

        danhSachLuaChon();

        int choice = nhapLuaChon();

        xuLyLuaChon(choice);
    }

	private void danhSachSanPhamHienCo() {
		this.listEl.xuatDanhSachThongTin();
		System.out.printf("\n\n");
	}

    private void danhSachLuaChon() {
       int n;
       System.out.print("Nhap so luong san pham thuc an nhanh can them: ");
    }

    private int nhapLuaChon() {
        Scanner ip = new Scanner(System.in);
        int n = 0;
		
		n = Validation.nhapDuLieuSo();	

        if(n <= 0) {
            throw new InputException("So luong san pham can them phai lon hon 0!!!");
        }

        return n;
    }

    private void xuLyLuaChon(int choice) {
        int n = choice;
        Display dp = Display.getInstance();
        Scanner ip = new Scanner(System.in);

        for(int i = 1; i <= n; ++i) {
            System.out.println("====================Nhap thong tin san pham thu " + i + "==================");
			Product sanPhamMoi;
			try {
				System.out.printf("1.Them thuc an nhanh\t\t2.Them thuc uong\n");

				int choiceProduct;
				
				System.out.print("Nhap lua chon: ");
				choiceProduct = Validation.nhapDuLieuSo();

				switch(choiceProduct) {
					case 1: 
						sanPhamMoi = new FastFood();
						break;
					case 2:
						sanPhamMoi = new Drinks();
						break;
					default:
						throw new InputException("Ban nhap sai lua chon, vui long nhap lai!!");
				}

				sanPhamMoi.nhap();

				// Kiểm tra dữ liệu nhập có bị trùng không
				boolean isEqualsMaSanPham = this.listEl.contains(sanPhamMoi.getMaSanPham());
				boolean isEqualsTenSanPham = this.listEl.contains(sanPhamMoi.getTenSanPham());
				if(isEqualsMaSanPham || isEqualsTenSanPham) {
					throw new InputException("Ma san pham hoac ten san pham da co, vui long nhap lai du lieu!!!");
				}

	            listEl.addElement(sanPhamMoi);
			}
			catch(RuntimeException ex) {
				System.out.println(ex.getMessage());
				i -= 1;
			}
        }

        DataBase dbFile = new DataBase("product");
        dbFile.update(listEl.writeListDataInDatabase());

        System.out.println("Them du lieu thanh cong!");
        System.out.println("Nhan nut bat ky de tiep tuc.");
        ip.nextLine();
        dp.hienThi(new DMenuProduct());
    }
}
