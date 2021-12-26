package menu.menuhoadon;

import components.comboproducts.ListComboProduct;
import components.hoadon.ListHoaDon;
import components.products.ListProduct;
import db.DataBase;
import lib.IHienThi;
import utils.Display;
import utils.DisplayFormat;

public class DThemHoaDon implements IHienThi {
    private ListHoaDon listhd;
	private ListProduct listpt;
	private ListComboProduct listcb;


    public DThemHoaDon(ListHoaDon listhd) {
        this.listhd=listhd;
		DataBase dbFile = new DataBase("product");
		this.listpt = new ListProduct();
		this.listpt.readListDataInDatabase(dbFile.find());
		dbFile = new DataBase("comboproduct");
		this.listcb = new ListComboProduct();
		this.listcb.readListDataInDatabase(dbFile.find());
    }
    
    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "THEM HOA DON", '='));
        System.out.printf("\n\n");
    }
    
    @Override
    public void xuat() {
		this.xuatDanhSach();
		this.listhd.xuatDanhSachThongTin();
		System.out.println("\n");
        this.themDuLieu();
    }
    
	private void xuatDanhSach() {
		this.listpt.xuatDanhSachThongTin();
		System.out.println("\n");
		this.listcb.xuatDanhSachThongTin();
		System.out.println("\n");
	}

    public void themDuLieu() {
        Display dp = Display.getInstance();
		this.listhd.nhapDanhSachThongTin();
		DataBase db = new DataBase("hoadon");
        db.update(this.listhd.writeListDataInDatabase());
        System.out.println("Them du lieu thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi((IHienThi)new DMenuHoaDon());
    }
}
