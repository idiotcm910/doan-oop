package components.products;

import utils.DateIn;
import utils.Validation;

import java.util.ArrayList;
import java.util.Scanner;

import lib.IFile;
import lib.InputException;

abstract public class Product implements IFile
{
	public static int gioiHanDoDaiMaSanPham = 4;
	public static int gioiHanDoDanTenSanPham = 20;

    protected String maSanPham;
    protected String tenSanPham;
    protected String ngayNhapThongTin;
    protected Double giaSanPham;
    
	public Product() {
		this.maSanPham = "";
		this.tenSanPham = "";
		this.ngayNhapThongTin = "";
		this.giaSanPham = 0.0;
	}

	public Product(String maSP, String tenSP, Double giaSP) {
		this.maSanPham = maSP;
		this.tenSanPham = tenSP;
		this.ngayNhapThongTin = DateIn.getCurrentDate();
		this.giaSanPham = giaSP;
	}

    public void nhap() {
        Scanner ip = new Scanner(System.in);
        System.out.print("Nhap ma san pham: ");

        this.maSanPham = Validation.nhapDuLieu(Product.gioiHanDoDaiMaSanPham);

		// Kiểm trả mã sản phẩm chỉ tối đa 4 ký tự
		if(this.maSanPham.length() > 4) {
			throw new InputException("Ma san pham chi duoc nhap toi da 4 ki tu!!!");
		}

        System.out.print("Nhap ten san pham: ");
		this.tenSanPham = Validation.nhapDuLieu(Product.gioiHanDoDanTenSanPham);

        System.out.print("Nhap gia: ");
		this.giaSanPham = Validation.nhapDuLieuSoThuc();

        this.ngayNhapThongTin = DateIn.getCurrentDate();
    }
    
    public String getMaSanPham() {
        return this.maSanPham;
    }
    
    public String getTenSanPham() {
        return this.tenSanPham;
    }
    
    public String getNgayNhapThongTin() {
        return this.ngayNhapThongTin;
    }
    
    public Double getGiaSanPham() {
        return this.giaSanPham;
    }
    
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
    
    public void setGiaSanPham(Double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }
    
	/**
	 *	hàm này xử lý các thuộc tính của đối tượng thành một mảng để truyền lên class mảng danh sách 
	 *	tạo bảng dữ liệu
	 *	@return ArrayList String, mảng các thuộc tính của đối tượng
	 * */
    abstract ArrayList<String> xuatMangThongTin();
    
    public boolean contains(String strProperty) {
		if(this.maSanPham.equals(strProperty)) { return true; }

		if(this.tenSanPham.equals(strProperty)) { return true; }

		String giaSanPhamToString = this.giaSanPham.toString();
		if(giaSanPhamToString.equals(strProperty)) { return true; }
	
		return false;
	}
}
