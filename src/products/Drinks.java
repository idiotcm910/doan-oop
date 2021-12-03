package products;

import java.util.StringTokenizer;
import java.util.Scanner;
import lib.IFile;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap dung tich: ");
        this.dungTich = scanner.nextLine();
        scanner.close();
    }
   
    @Override
    public String xuatThongTin() {
        return String.format("%-10s%-25s%-16s%-16s%-29s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.dungTich, this.ngayNhapThongTin);
    }
     
    @Override
    public boolean contains(String s) {
        return this.maSanPham == s || this.tenSanPham == s || Integer.toString(this.giaSanPham) == s || this.dungTich == s;
    }
    
    @Override
    public boolean equals(Object o) {
        Drinks drinks = (Drinks)o;
        return drinks.getMaSanPham() == this.maSanPham && drinks.getTenSanPham() == this.tenSanPham && drinks.getGiaSanPham() == this.giaSanPham && drinks.getDungTich() == this.dungTich;
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
