package components.products;

import java.util.StringTokenizer;
import java.util.ArrayList;
import utils.Validation;

public class Drinks extends Product 
{
    private Integer dungTich;
    
	public Drinks() {
		super();
		this.dungTich = 0;
	}

	public Drinks(String maSP, String tenSP, Double giaSP, Integer dungTich) {
		super(maSP, tenSP, giaSP);
		this.dungTich = dungTich;
	}

    public Integer getDungTich() {
        return this.dungTich;
    }
    
    public void setDungTich(Integer dungTich) {
        this.dungTich = dungTich;
    }

    public void nhap() {
        super.nhap();
        System.out.print("Nhap dung tich(ml): ");
		this.dungTich = Validation.nhapDuLieuSo(1, 500);
    }
   
    @Override
    public ArrayList<String> xuatMangThongTin() {
		ArrayList<String> data = new ArrayList<String>();

		data.add(this.maSanPham);
		data.add(this.tenSanPham);
		data.add(this.giaSanPham.toString() + "$");
		data.add(this.dungTich.toString() + "ml");
		data.add(this.ngayNhapThongTin);

		return data;
    }
     
    @Override
    public boolean contains(String s) {
		if(super.contains(s)) { return true; }

		String dungTichToString = Integer.toString(this.dungTich);
		if(dungTichToString.equals(s)) { return true; }

		return false;
	}
    
    @Override
    public void readDataInDatabase(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        this.maSanPham = stringTokenizer.nextToken();
        this.tenSanPham = stringTokenizer.nextToken();
        this.giaSanPham = Double.parseDouble(stringTokenizer.nextToken());
        this.dungTich = Integer.parseInt(stringTokenizer.nextToken());
        this.ngayNhapThongTin = stringTokenizer.nextToken();
    }
     
    @Override
    public String writeDataInDatabase() {
        return String.format("%s;%s;%s;%s;%s", this.maSanPham, this.tenSanPham, this.giaSanPham, this.dungTich, this.ngayNhapThongTin);
    }
}
