package menu.menucombo;

import components.comboproducts.ComboProduct;
import components.comboproducts.ListComboProduct;

import db.DataBase;
import lib.IHienThi;
import lib.InputException;
import utils.Display;
import utils.DisplayFormat;
import utils.Validation;

public class DXoaCombo implements IHienThi {
    private ListComboProduct danhSachCombo;
    public DXoaCombo(ListComboProduct list) {
        this.danhSachCombo = list;
    }

    @Override
    public void xuatTitle() {
        System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "XOA COMBO SAN PHAM", '='));
        System.out.printf("\n\n");
    }

    @Override
    public void xuat() {
        this.danhSachComboSanPham();

        String maCombo = this.nhapMaComboSanPham();

        xuLyMaComboSanPham(maCombo);
    }

    private void danhSachComboSanPham() {
        this.danhSachCombo.xuatDanhSachThongTin();
		System.out.print("\n");
    }

    private String nhapMaComboSanPham() {
        String str;
        System.out.print("Nhap ma combo san pham can xoa: ");
		str = Validation.nhapDuLieu(ComboProduct.gioihanDoDaiMaCombo);
        return str;
    }

    private void xuLyMaComboSanPham(String str) {
        int indexOfComboProduct = this.danhSachCombo.indexOf(str);

        if(indexOfComboProduct == -1) {
            throw new InputException("Ma combo san pham ban nhap khong tim thay!!!");
        }
        
        this.danhSachCombo.removeElement(indexOfComboProduct);

        DataBase dbFile = new DataBase("comboproduct");
        dbFile.update(this.danhSachCombo.writeListDataInDatabase());

        Display dp = Display.getInstance();
        System.out.println("Xoa combo thanh cong!");
		DisplayFormat.dungChuongTrinh();
        dp.hienThi(new DMenuCombo());
    }
}
