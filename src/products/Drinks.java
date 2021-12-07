package products;

import java.util.StringTokenizer;
import java.util.Scanner;
import lib.IFile;
import lib.InputException;

public class Drinks extends Product implements IFile
{
    private String dungTich;
    
    public String getDungTich() {
        return this.dungTich;
    }
    
    public void setDungTich(String dungTich) {
        this.dungTich = dungTich;
    }

    public void nhap() {
        super.nhap();
        Scanner ip = new Scanner(System.in);

        System.out.print("Nhap dung tich: ");
        this.dungTich = ip.nextLine().trim();
		if(this.dungTich.isEmpty()) {
			throw new InputException("Dung tich khong duoc bo trong!!!");
		}
    }
   
    @Override
    public String xuatThongTin() {
        return String.format("%-10s%-25s%-11s%-16s%-25s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.dungTich, this.ngayNhapThongTin);
    }
     
    @Override
    public boolean contains(String s) {
		if(this.maSanPham.equals(s)) { return true; }

		if(this.tenSanPham.equals(s)) { return true; }

		String giaSanPhamToString = Integer.toString(this.giaSanPham);
		if(giaSanPhamToString.equals(s)) { return true; }

		if(this.dungTich.equals(s)) { return true; }

		return false;
	}
    
    @Override
    public boolean equals(Object o) {
        Drinks ff = (Drinks)o;

		if(this.maSanPham.equals(ff.getMaSanPham())) { return false; }

		if(this.tenSanPham.equals(ff.getTenSanPham())) { return false; }

		if(this.giaSanPham == ff.getGiaSanPham()) { return false; }

		if(this.dungTich.equals(ff.getDungTich())) { return false; }

		return true;
	}
    
    @Override
    public void readDataInDatabase(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        this.maSanPham = stringTokenizer.nextToken();
        this.tenSanPham = stringTokenizer.nextToken();
        this.giaSanPham = Integer.parseInt(stringTokenizer.nextToken());
        this.dungTich = stringTokenizer.nextToken();
        this.ngayNhapThongTin = stringTokenizer.nextToken();
    }
     
    @Override
    public String writeDataInDatabase() {
        return String.format("%s;%s;%s;%s;%s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.dungTich, this.ngayNhapThongTin);
    }
}
