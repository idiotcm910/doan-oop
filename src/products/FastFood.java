package products;

import java.util.StringTokenizer;
import java.util.Scanner;
import lib.IFile;

public class FastFood extends Product implements IFile
{
    private String hamLuongDinhDuong;
    
    public void nhap() {
        super.nhap();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ham luong dinh duong: ");
        this.hamLuongDinhDuong = scanner.nextLine();
    }
    
    public String getHamLuongDinhDuong() {
        return this.hamLuongDinhDuong;
    }
    
    public void setHamLuongDinhDuong(String hamLuongDinhDuong) {
        this.hamLuongDinhDuong = hamLuongDinhDuong;
    }
     
    @Override
    public boolean contains(String s) {
        return this.maSanPham.equals(s) || this.tenSanPham.equals(s) || Integer.toString(this.giaSanPham).equals(s) || this.hamLuongDinhDuong.equals(s);
    }
     
    @Override
    public String xuatThongTin() {
        return String.format("%-10s%-25s%-11s%-16s%-15s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.hamLuongDinhDuong, this.ngayNhapThongTin);
    }
     
    @Override
    public boolean equals(Object o) {
        FastFood fastFood = (FastFood)o;
        return fastFood.getMaSanPham() == this.maSanPham && fastFood.getTenSanPham() == this.tenSanPham && fastFood.getGiaSanPham() == this.giaSanPham && fastFood.getHamLuongDinhDuong() == this.hamLuongDinhDuong;
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
