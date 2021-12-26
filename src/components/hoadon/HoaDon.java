package components.hoadon;

import java.util.ArrayList;
import java.util.StringTokenizer;

import components.comboproducts.ComboProduct;
import components.comboproducts.ListComboProduct;
import components.products.ListProduct;
import components.products.Product;
import db.DataBase;

import lib.IFile;
import lib.InputException;
import utils.DateIn;
import utils.Validation;

public class HoaDon implements IFile {
    private String maHoaDon;
    private ArrayList<String> danhSachTenHang;
	private ArrayList<Integer> soLuong;
    private String ngayNhapThongTin;
    private Double donGia;
public HoaDon(){
    this.maHoaDon = "";
    this.danhSachTenHang = new ArrayList<String>();
	this.soLuong = new ArrayList<Integer>();
    this.donGia = 0.0;
}

	public HoaDon(String maHoaDon, ArrayList<String> danhSachTenHang, ArrayList<Integer> soLuong, Double donGia) {
		this.maHoaDon = maHoaDon;
		this.danhSachTenHang = danhSachTenHang;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.ngayNhapThongTin = DateIn.getCurrentDate();
	}
	public String getmaHoaDon(){
            return maHoaDon;
    }

	public void setmaHoaDon(String maHoaDon){
            this.maHoaDon=maHoaDon;
     }
        
        public ArrayList<String> getdanhSachTenHang(){
            return danhSachTenHang;
        }
        public void setdanhSachTenHang(ArrayList<String> danhSachTenHang){
            this.danhSachTenHang=danhSachTenHang;
        }
        public ArrayList<Integer> getsoLuong(){
            return soLuong;
        }
        public void setsoLuong(ArrayList<Integer> soLuong){
            this.soLuong=soLuong;
        }
        
        public String getngayNhapThongTin(){
            return ngayNhapThongTin;
            
        }
        
        public void setngayNhapThongTin(String ngayNhapThongTin){
            this.ngayNhapThongTin = ngayNhapThongTin;
        }
        
        public Double getdonGia(){
            return donGia;
        }
        
        public void setdonGia(Double donGia){
            this.donGia = donGia;
        }

public void nhap(){
    System.out.print("nhap ma hoa don: ");
    this.maHoaDon = Validation.nhapDuLieu(4);

	int soLuongHang = 0;
	System.out.print("nhap so luong hang: ");
	soLuongHang = Validation.nhapDuLieuSo(1, 100);

	DataBase dbFileProduct = new DataBase("product");
	DataBase dbFileCombo = new DataBase("comboproduct");

	ListProduct danhSachProduct = new ListProduct();
	ListComboProduct danhSachComboProduct = new ListComboProduct();
	danhSachProduct.readListDataInDatabase(dbFileProduct.find());
	danhSachComboProduct.readListDataInDatabase(dbFileCombo.find());

	for(int i = 0; i < soLuongHang; ++i) {
		try {
			String ma;
			System.out.print("Nhap ma hang hoa thu " + (i + 1) + ": ");
			ma = Validation.nhapDuLieu();
			boolean check = false;
			int index;
			Double gia = 0.0;
			if((index = danhSachProduct.indexOf(ma)) != -1) {
				Product pt = danhSachProduct.getElement(index);
				this.danhSachTenHang.add(pt.getTenSanPham());
				gia = pt.getGiaSanPham();
				check = true;
			}
			if(check != true && (index = danhSachComboProduct.indexOf(ma)) != -1) {
				ComboProduct cb = danhSachComboProduct.getElement(index);
				this.danhSachTenHang.add(cb.getTenComboSanPham());
				gia = cb.getGiaCombo();
				check = true;
			}
	
			if(check == false) {
				throw new InputException("ma san pham ban nhap khong ton tai!!!");
			}

			System.out.print("Nhap so luong san pham thu " + (i + 1) + ": ");
			int soLuongSanPham = Validation.nhapDuLieuSo(1, 100);
			this.soLuong.add(soLuongSanPham);
			this.donGia += gia * soLuongSanPham;
		}
		catch(InputException ex) {
			System.out.println(ex.getMessage());
			i -= 1;
		}
	}
	
    ngayNhapThongTin = DateIn.getCurrentDate();
}
public ArrayList<String> xuatMangThongTin(){

	ArrayList<String> array = new ArrayList<String>();
	array.add(this.maHoaDon);
	String sanPhamVaSoLuong = "";
	for(int i = 0; i < this.danhSachTenHang.size(); ++i) {
		sanPhamVaSoLuong += this.soLuong.get(i) + " " + this.danhSachTenHang.get(i) + ", ";
	}
	sanPhamVaSoLuong = sanPhamVaSoLuong.substring(0, sanPhamVaSoLuong.length() - 2);
	array.add(sanPhamVaSoLuong);
	array.add(this.donGia.toString() + "$");
	array.add(this.ngayNhapThongTin);
    return array;
}  
	
	@Override
	public void readDataInDatabase(String data) {
		StringTokenizer strToken = new StringTokenizer(data, ";");
		this.maHoaDon = strToken.nextToken();
		StringTokenizer subStrToken = new StringTokenizer(strToken.nextToken(), ":");
		while(subStrToken.hasMoreTokens()) {
			StringTokenizer strTokenChild = new StringTokenizer(subStrToken.nextToken(), "\\");
			this.soLuong.add(Integer.parseInt(strTokenChild.nextToken()));
			this.danhSachTenHang.add(strTokenChild.nextToken());
		}
		this.donGia = Double.parseDouble(strToken.nextToken());
		this.ngayNhapThongTin = strToken.nextToken();
	}

	@Override
	public String writeDataInDatabase() {
		String data = "";
		data += this.maHoaDon + ";";
		String dataSanPhamVaSoLuong = "";
		for(int i = 0; i < this.danhSachTenHang.size(); ++i) {
			dataSanPhamVaSoLuong += this.soLuong.get(i) + "\\" + this.danhSachTenHang.get(i) + ":";
		}
		dataSanPhamVaSoLuong = dataSanPhamVaSoLuong.substring(0, dataSanPhamVaSoLuong.length() - 1);
		data += dataSanPhamVaSoLuong + ";";
		data += this.donGia + ";";
		data += this.ngayNhapThongTin;
		return data;
	}

	public boolean contains(String str) {
		if(this.maHoaDon.equals(str)) { return true; }
		if(this.danhSachTenHang.contains(str)) { return true; }
		if(str.equals(this.donGia.toString())) { return true; }
		if(this.ngayNhapThongTin.equals(str)) { return true; }
		return false;
	}
        


}	
    


