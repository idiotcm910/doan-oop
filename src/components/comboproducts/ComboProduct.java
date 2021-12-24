package components.comboproducts;

import lib.IFile;
import lib.InputException;
import utils.Validation;

import java.util.ArrayList;
import java.util.StringTokenizer;
import db.DataBase;

public class ComboProduct implements IFile {

	public static final int gioiHanSoLuongSanPham = 5;	
	public static final int gioihanDoDaiMaCombo = 4;
	public static final int gioiHanDoDaiTenCombo = 20;

	private String maComboSanPham;
	private String tenComboSanPham;
	private ArrayList<String> danhSachTenSanPham;
	private ArrayList<Integer> soLuongMoiSanPham;
	private Double giaCombo;
	private int soNguoiSuDung;

	public ComboProduct() {
		maComboSanPham = "";
		tenComboSanPham = "";
		danhSachTenSanPham = new ArrayList<String>();
		soLuongMoiSanPham = new ArrayList<Integer>();
	}

	public ComboProduct(String maCombo, String tenCombo, ArrayList<String> danhSachSP, ArrayList<Integer> soLuongMoiSP, Double giaCombo, int soLuongSuDung) {
		this.maComboSanPham = maCombo;
		this.tenComboSanPham = tenCombo;
		this.danhSachTenSanPham = danhSachSP;
		this.soLuongMoiSanPham = soLuongMoiSP;
		this.giaCombo = giaCombo;
		this.soNguoiSuDung = soLuongSuDung;
	}
	
	// GET
	public String getMaComboSanPham() { return this.maComboSanPham; }
	public String getTenComboSanPham() { return this.tenComboSanPham; }
	public ArrayList<String> getDanhSachSanPham() { return this.danhSachTenSanPham; }
	public Double getGiaCombo() { return this.giaCombo; }
	public int getSoNguoiSuDung() { return this.soNguoiSuDung; }

	//SET
	public void setMaComboSanPham(String str) { this.maComboSanPham = str; }
	public void setTenComboSanPham(String str) { this.tenComboSanPham = str; }
	public void setDanhSachSanPham(ArrayList<String> ds) { this.danhSachTenSanPham = ds; }		
	public void setGiaCombo(Double gia) { this.giaCombo = gia; }
	public void setSoNguoiSuDung(int soLuong) { this.soNguoiSuDung = soLuong; }


	private String chuoiThongTinSanPhamVaSoLuong() {
		String str = "";
		
		for(int i = 0; i < danhSachTenSanPham.size(); ++i) {
			str += soLuongMoiSanPham.get(i) + " " + this.danhSachTenSanPham.get(i) + ", "; 
		}

		return str.substring(0, str.length() - 2);
	}

	// Trả về mảng dữ liệu để danh sách xuất ra bảng dữ liệu
	public ArrayList<String> xuatThongTinComBo() {
		ArrayList<String> data = new ArrayList<String>();

		data.add(this.maComboSanPham);
		data.add(this.tenComboSanPham);
		data.add(this.chuoiThongTinSanPhamVaSoLuong());
		data.add(this.giaCombo.toString() + "$");
		data.add(Integer.toString(this.soNguoiSuDung) + " Nguoi");

		return data;
	}
	
	public void nhapThongTin() {
		DataBase dbFile = new DataBase("product");

		System.out.print("Nhap ma combo: ");
		this.maComboSanPham = Validation.nhapDuLieu(ComboProduct.gioihanDoDaiMaCombo);

		System.out.print("Nhap ten combo: ");
		this.tenComboSanPham = Validation.nhapDuLieu(ComboProduct.gioiHanDoDaiTenCombo);
		
		// Nhap danh sach san pham trong combo
		int soLuongSanPham = 0;
		System.out.print("Nhap so luong san pham trong combo: ");
		soLuongSanPham = Validation.nhapDuLieuSo(1, ComboProduct.gioiHanSoLuongSanPham);
		if(soLuongSanPham > ComboProduct.gioiHanSoLuongSanPham) {
			throw new InputException("So luong san pham chi duoc toi da " + ComboProduct.gioiHanSoLuongSanPham + "!!!");
		}

		for(int i = 0; i < soLuongSanPham; ++i) {
			String sanPham;
			int soLuong;

			System.out.print("Nhap ten san pham thu " + (i + 1) + ": ");
			sanPham = Validation.nhapDuLieu();
			System.out.print("Nhap so luong san pham thu " + (i + 1) + ": ");
			soLuong = Validation.nhapDuLieuSo(1, 100);

			this.danhSachTenSanPham.add(sanPham);
			this.soLuongMoiSanPham.add(soLuong);
		}

		// kiem tra cac san pham trong combo co ton tai trong du lieu khong.
		ArrayList<String> data = dbFile.findMany(this.danhSachTenSanPham);
		if(data.size() != this.danhSachTenSanPham.size()) {
			throw new InputException("Mot hay nhieu ten san pham ban nhap khong ton tai trong cua hang!!!"); 
		}

		System.out.print("Nhap gia combo: ");
		this.giaCombo = Validation.nhapDuLieuSoThuc();

		System.out.print("Nhap so nguoi su dung combo: ");
		this.soNguoiSuDung = Validation.nhapDuLieuSo(1, 10);
	}

	public boolean contains(String str) {
		if(this.maComboSanPham.equals(str)) { return true; }

		if(this.tenComboSanPham.equals(str)) { return true; }

		if(this.danhSachTenSanPham.contains(str)) { return true; }
		
		String giaComboToString = this.giaCombo.toString();
		if(giaComboToString.equals(str)) { return true; }

		String soNguoiSuDungToString = Integer.toString(this.soNguoiSuDung);
		if(soNguoiSuDungToString.equals(str)) { return true; }

		return false;
	}

	/*
	 * Dữ liệu class comboProduct trong file được lưu theo cấu trúc đã được định nghĩa trong class DataBase 
	 * Combo A;C001;1\\005:3\\DTG:6\\YTGH;40;5
	 * Các dữ liệu của sản phẩm trong combo chỉlưu mã sản phẩm dưới file
	 */
	private void readDataSanPhamVaSoLuongMoiSanPham(String data) {
		StringTokenizer strToken = new StringTokenizer(data, ":");

		while(strToken.hasMoreTokens()) {
			String stringInData = strToken.nextToken();
			StringTokenizer strTokenChild = new StringTokenizer(stringInData, "\\");

			String strSoLuongMoiSanPham = strTokenChild.nextToken();
			this.soLuongMoiSanPham.add(Integer.parseInt(strSoLuongMoiSanPham));

			this.danhSachTenSanPham.add(strTokenChild.nextToken());
		}
	}

	@Override
	public void readDataInDatabase(String data) {
        StringTokenizer strToken = new StringTokenizer(data, ";");

		this.maComboSanPham = strToken.nextToken();

		this.tenComboSanPham = strToken.nextToken();

		this.readDataSanPhamVaSoLuongMoiSanPham(strToken.nextToken());

		String strGiaCombo = strToken.nextToken();
		this.giaCombo = Double.parseDouble(strGiaCombo);

		String strSoNguoi = strToken.nextToken();
		this.soNguoiSuDung = Integer.parseInt(strSoNguoi);
	}

	private String writeDataSanPhamVaSoLuongMoiSanPham() {
		String data = "";

		for(int i = 0; i < this.danhSachTenSanPham.size(); ++i) {
			int soLuongSanPham = this.soLuongMoiSanPham.get(i);
			String tenSanPham = this.danhSachTenSanPham.get(i);
			data += soLuongSanPham + "\\" + tenSanPham + ":";
		}

		return data.substring(0, data.length() - 1);
	}

	@Override
	public String writeDataInDatabase() {
		return String.format("%s;%s;%s;%s;%s", this.maComboSanPham, this.tenComboSanPham, this.writeDataSanPhamVaSoLuongMoiSanPham(), this.giaCombo, this.soNguoiSuDung);
	}
} 
