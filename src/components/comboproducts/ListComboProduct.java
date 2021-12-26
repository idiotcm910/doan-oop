package components.comboproducts;

import java.util.ArrayList;
import java.util.Arrays;

import lib.IListFile;
import lib.InputException;
import utils.DisplayFormat;
import lib.IListElement;
import utils.TableConsole;
import utils.Validation;


public class ListComboProduct implements IListFile, IListElement {
	private ArrayList<ComboProduct> danhSachCombo;

	public ListComboProduct() {
		this.danhSachCombo = new ArrayList<ComboProduct>();
	}

	public ListComboProduct(ArrayList<ComboProduct> list) {
		this.danhSachCombo = list;
	}

   	public boolean addElement(Object obj) {
		ComboProduct pt = (ComboProduct)obj;
      	return this.danhSachCombo.add(pt);
   	}

   	public ComboProduct getElement(int index) {
      	return this.danhSachCombo.get(index);
   	}

   	public boolean setElement(int index, Object obj) {
		ComboProduct pt = (ComboProduct)obj;
		return (this.danhSachCombo.set(index, pt) != null)? true : false;
   	}

	@Override
	public void nhapDanhSachThongTin() {
       	System.out.print("Nhap so luong combo san pham nhanh can them: ");
    	int n = 0;
		n = Validation.nhapDuLieuSo(1, 10);	

        for(int i = 1; i <= n; ++i) {
            System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay / 2, "Nhap thong tin combo thu " + i, '='));
			ComboProduct comboMoi = new ComboProduct();
			try {
				comboMoi.nhapThongTin();

				// Kiểm tra dữ liệu nhập có bị trùng không
				boolean isEqualsMaComboSanPham = this.contains(comboMoi.getMaComboSanPham());
				boolean isEqualsTenComboSanPham = this.contains(comboMoi.getTenComboSanPham());
				if(isEqualsMaComboSanPham || isEqualsTenComboSanPham) {
					throw new InputException("Ma combo san pham hoac ten combo san pham da co, vui long nhap lai du lieu!!!");
				}

	            this.addElement(comboMoi);
			}
			catch(RuntimeException ex) {
				System.out.println(ex.getMessage());
				i -= 1;
			}
        }

	}

   	@Override
   	public void xuatDanhSachThongTin() {
		Integer[] arrayWidth = {10, 20, 25, 65, 14, 24};	
		String[] arrayTitle = {"STT", "Ma combo", "Ten combo", "Danh sach san pham", "Gia", "So nguoi dung"};	

		// tao bang
		ArrayList<Integer> arrayWidthColumns = new ArrayList<Integer>(Arrays.asList(arrayWidth));
		ArrayList<String> arrayTitleColumns = new ArrayList<String>(Arrays.asList(arrayTitle));
		TableConsole table = new TableConsole(arrayWidthColumns);

		//set title
		table.setTitle(arrayTitleColumns);

		// them hang du lieu vao bang
		for(int i = 0; i < this.danhSachCombo.size(); ++i) {
			ComboProduct elCombo = this.danhSachCombo.get(i);
			ArrayList<String> hangDuLieu = new ArrayList<String>();
			
			hangDuLieu.add(Integer.toString(i + 1)); // them so thu tu
			hangDuLieu.addAll(elCombo.xuatThongTinComBo());
			table.addRowData(hangDuLieu);
		}

		// in ra bang du lieu trong console
		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "DANH SACH COMBO SAN PHAM", '-'));
		table.DrawTable();	
   	}

	@Override
	public boolean isEmpty() {
		return this.danhSachCombo.isEmpty();	
	}

   	@Override
   	public int size() {
      	return this.danhSachCombo.size();
   	}

   	@Override
   	public boolean contains(String strProperty) {
        for(ComboProduct el : danhSachCombo) {
            if(el.contains(strProperty)) {
                return true;
            }
        }

        return false;
   	}

   	@Override
	public int indexOf(String strProperty) {
		for(int i = 0; i < this.danhSachCombo.size(); ++i) {
			ComboProduct el = this.danhSachCombo.get(i);
			
			if(el.contains(strProperty)) {
				return i;
			}
		}

		return -1;
	}

   	@Override
   	public boolean removeElement(int index) {
		return (this.danhSachCombo.remove(index) != null)? true : false;
   	}

   	@Override
   	public void readListDataInDatabase(ArrayList<String> collection) {
       	for(String data : collection) {
        	ComboProduct pt = new ComboProduct();
			
			pt.readDataInDatabase(data);
           	this.danhSachCombo.add(pt);
       	}
   	}

   	@Override
   	public ArrayList<String> writeListDataInDatabase() {
      	ArrayList<String> collection = new ArrayList<String>();

      	for(ComboProduct el : danhSachCombo) {
			String data = el.writeDataInDatabase();
           	collection.add(data);
      	}

      	return collection;
   	}
}
