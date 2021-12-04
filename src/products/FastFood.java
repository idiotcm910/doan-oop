package products;

import java.util.StringTokenizer;
import java.util.Scanner;
import lib.IFile;
import lib.InputException;

public class FastFood extends Product implements IFile
{
    private String hamLuongDinhDuong;
    
    public void nhap() {
        super.nhap();
        Scanner ip = new Scanner(System.in);

        System.out.print("Nhap ham luong dinh duong: ");
        this.hamLuongDinhDuong = ip.nextLine().trim();
		if(this.hamLuongDinhDuong.isEmpty()) {
			throw new InputException("Ham luong dinh duong khong duoc bo trong!!!");
		}
    }
    
    public String getHamLuongDinhDuong() {
        return this.hamLuongDinhDuong;
    }
    
    public void setHamLuongDinhDuong(String hamLuongDinhDuong) {
        this.hamLuongDinhDuong = hamLuongDinhDuong;
    }
     
    @Override
    public boolean contains(String s) {
		if(this.maSanPham.equals(s)) { return true; }

		if(this.tenSanPham.equals(s)) { return true; }

		String giaSanPhamToString = Integer.toString(this.giaSanPham);
		if(giaSanPhamToString.equals(s)) { return true; }

		if(this.hamLuongDinhDuong.equals(s)) { return true; }

		return false;
	}

    @Override
    public String xuatThongTin() {
        return String.format("%-10s%-25s%-11s%-16s%-15s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.hamLuongDinhDuong, this.ngayNhapThongTin);
    }
     
    @Override
    public boolean equals(Object o) {
        FastFood ff = (FastFood)o;

		if(this.maSanPham.equals(ff.getMaSanPham())) { return false; }

		if(this.tenSanPham.equals(ff.getTenSanPham())) { return false; }

		if(this.giaSanPham == ff.getGiaSanPham()) { return false; }

		if(this.hamLuongDinhDuong.equals(ff.getHamLuongDinhDuong())) { return false; }

		return true;
	}
     
    @Override
    public void readDataInDatabase(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        this.maSanPham = stringTokenizer.nextToken();
        this.tenSanPham = stringTokenizer.nextToken();
        this.giaSanPham = Integer.parseInt(stringTokenizer.nextToken());
        this.hamLuongDinhDuong = stringTokenizer.nextToken();
        this.ngayNhapThongTin = stringTokenizer.nextToken();
    }
     
    @Override
    public String writeDataInDatabase() {
        return String.format("%s;%s;%s;%s;%s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.hamLuongDinhDuong, this.ngayNhapThongTin);
    }
}
