package components.products;

import java.util.StringTokenizer;
import java.util.ArrayList;
import utils.Validation;

public class FastFood extends Product
{
    private Integer hamLuongDinhDuong;
    
	public FastFood() {
		super();
		this.hamLuongDinhDuong = 0;
	}

	public FastFood(String maSP, String tenSP, Double giaSP, Integer hamLuongDinhDuong) {
		super(maSP, tenSP, giaSP);
		this.hamLuongDinhDuong = hamLuongDinhDuong;
	}

    public void nhap() {
        super.nhap();

        System.out.print("Nhap ham luong dinh duong(kcal): ");
		this.hamLuongDinhDuong = Validation.nhapDuLieuSo(1, 500);
    }
    
    public Integer getHamLuongDinhDuong() {
        return this.hamLuongDinhDuong;
    }
    
    public void setHamLuongDinhDuong(Integer hamLuongDinhDuong) {
        this.hamLuongDinhDuong = hamLuongDinhDuong;
    }
     
    @Override
    public boolean contains(String s) {
		if(super.contains(s)) { return true; }

		String hamLuongddToString = this.hamLuongDinhDuong.toString();
		if(hamLuongddToString.equals(s)) { return true; }

		return false;
	}

    @Override
    public ArrayList<String> xuatMangThongTin() {
		ArrayList<String> data = new ArrayList<String>();

		data.add(this.maSanPham);
		data.add(this.tenSanPham);
		data.add(this.giaSanPham.toString() + "$");
		data.add(this.hamLuongDinhDuong.toString() + "kcal");
		data.add(this.ngayNhapThongTin);

		return data;
    }
     
    @Override
    public void readDataInDatabase(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        this.maSanPham = stringTokenizer.nextToken();
        this.tenSanPham = stringTokenizer.nextToken();
        this.giaSanPham = Double.parseDouble(stringTokenizer.nextToken());
        this.hamLuongDinhDuong = Integer.parseInt(stringTokenizer.nextToken());
        this.ngayNhapThongTin = stringTokenizer.nextToken();
    }
     
    @Override
    public String writeDataInDatabase() {
        return String.format("%s;%s;%s;%s;%s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.hamLuongDinhDuong, this.ngayNhapThongTin);
    }
}
