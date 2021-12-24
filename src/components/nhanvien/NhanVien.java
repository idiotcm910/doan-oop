package components.nhanvien;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;
import lib.IFile;
import utils.Validation;

public class NhanVien extends ConNguoi implements IFile
{
    private String Ma;
    private String NgayVaoLam;
    private String Vitri;
    private String Luong;
    
    public NhanVien() {
    }
    
    public NhanVien(String HoTen, String GioiTinh, String NgaySinh, String SoDienThoai, String CMND, String Ma, String NgayVaoLam, String Vitri, String Luong) {
        super(HoTen, GioiTinh, NgaySinh, SoDienThoai, CMND);
        this.Ma = Ma;
        this.NgayVaoLam = NgayVaoLam;
        this.Vitri = Vitri;
        this.Luong = Luong;
    }
    
    public String getMa() {
        return this.Ma;
    }
    
    public void setMa(String Ma) {
        this.Ma = Ma;
    }
    
    public String getNgayVaoLam() {
        return this.NgayVaoLam;
    }
    
    public void setNgayVaoLam(String NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }
    
    public String getVitri() {
        return this.Vitri;
    }
    
    public void setVitri(String Vitri) {
        this.Vitri = Vitri;
    }
    
    public String getLuong() {
        return this.Luong;
    }
    
    public void setLuong(String Luong) {
        this.Luong = Luong;
    }
    
    public void nhap() {
        super.nhapthongtin();
        System.out.print("Nhap Ma: ");
		this.Ma = Validation.nhapDuLieu(4);
        System.out.print("Nhap ngay vao lam: ");
		this.NgayVaoLam = Validation.nhapDuLieu();
        System.out.print("Nhap vi tri: ");
		this.Vitri = Validation.nhapDuLieu();
        System.out.print("Nhap luong: ");
		this.Luong = Validation.nhapDuLieu();
    }
    
    @Override
	public ArrayList<String> xuatThongTin() {
		ArrayList<String> array = new ArrayList<String>();
		array.add(this.HoTen);
		array.add(this.GioiTinh);
		array.add(this.NgaySinh);
		array.add(this.SoDienThoai);
		array.add(this.CMND);
		array.add(this.Ma);
		array.add(this.NgayVaoLam);
		array.add(this.Vitri);
		array.add(this.Luong);
		return array;
	}
    
    @Override
    public void readDataInDatabase(String data) {
        StringTokenizer sc = new StringTokenizer(data, ";");
        this.HoTen = sc.nextToken();
        this.GioiTinh = sc.nextToken();
        this.NgaySinh = sc.nextToken();
        this.SoDienThoai = sc.nextToken();
        this.CMND = sc.nextToken();
        this.Ma = sc.nextToken();
        this.NgayVaoLam = sc.nextToken();
        this.Vitri = sc.nextToken();
        this.Luong = sc.nextToken();
    }
    
    @Override
    public String writeDataInDatabase() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;", this.HoTen, this.GioiTinh, this.NgaySinh, this.SoDienThoai, this.CMND, this.Ma, this.NgayVaoLam, this.Vitri, this.Luong);
    }
    
    @Override
    public boolean equals(Object o) {
        NhanVien nv = (NhanVien)o;
        return nv.getHoTen() == this.HoTen && nv.getGioiTinh() == this.GioiTinh && nv.getNgaySinh() == this.NgaySinh && nv.getCMND() == this.CMND && nv.getMa() == this.Ma && nv.getNgayVaoLam() == this.NgayVaoLam && nv.getVitri() == this.Vitri && nv.getLuong() == this.Luong;
    }
    
    @Override
    public boolean contains(String s) {
        return this.HoTen.equals(s) || this.GioiTinh.equals(s) || this.NgaySinh.equals(s) || this.SoDienThoai.equals(s) || this.CMND.equals(s) || this.Ma.equals(s) || this.NgayVaoLam.equals(s) || this.Vitri.equals(s) || this.Luong.equals(s);
    }
}
