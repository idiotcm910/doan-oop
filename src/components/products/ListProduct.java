package components.products;

import java.util.ArrayList;
import java.util.Arrays;

import lib.IFile;
import lib.IListFile;
import lib.InputException;
import utils.DisplayFormat;
import lib.IListElement;
import utils.TableConsole;
import utils.Validation;

public class ListProduct implements IListFile, IListElement {
   	private ArrayList<Product> listElement = new ArrayList<Product>();

   	public boolean addElement(Object obj) {
		Product pt = (Product)obj;
      	return this.listElement.add(pt);
   	}

   	public Product getElement(int index) {
      	return this.listElement.get(index);
   	}

   	public boolean setElement(int index, Object obj) {
		Product pt = (Product)obj;
		return (this.listElement.set(index, pt) != null)? true : false;
   	}

	@Override
	public boolean isEmpty() {
		return this.listElement.isEmpty();
	}

	@Override
	public void nhapDanhSachThongTin() {
		System.out.print("Nhap so luong san pham can them: ");
        int n = 0;
		n = Validation.nhapDuLieuSo(0, 100);	

        for(int i = 1; i <= n; ++i) {
            System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay / 2, "Nhap thong tin san pham thu " + i, '='));
			Product sanPhamMoi;
			try {
				System.out.printf("1.Them thuc an nhanh\t\t2.Them thuc uong\n");

				int choiceProduct;
				
				System.out.print("Nhap lua chon: ");
				choiceProduct = Validation.nhapDuLieuSo();

				switch(choiceProduct) {
					case 1: 
						sanPhamMoi = (Product)new FastFood();
						break;
					case 2:
						sanPhamMoi = (Product)new Drinks();
						break;
					default:
						throw new InputException("Ban nhap sai lua chon, vui long nhap lai!!");
				}

				sanPhamMoi.nhap();

				// Kiểm tra dữ liệu nhập có bị trùng không
				boolean isEqualsMaSanPham = this.contains(sanPhamMoi.getMaSanPham());
				boolean isEqualsTenSanPham = this.contains(sanPhamMoi.getTenSanPham());
				if(isEqualsMaSanPham || isEqualsTenSanPham) {
					throw new InputException("Ma san pham hoac ten san pham da co, vui long nhap lai du lieu!!!");
				}

	            this.addElement(sanPhamMoi);
			}
			catch(RuntimeException ex) {
				System.out.println(ex.getMessage());
				i -= 1;
			}
        }

	}

   	@Override
   	public void xuatDanhSachThongTin() {
		Integer[] arrayWidth = {10, 20, 40, 16, 21, 25, 26};
		String[] arrayTitle = {"STT", "Ma san pham", "Ten San Pham", "gia", "hldd/dt", "Ngay nhap", "Loai san pham"};
		
		// tao bang
		ArrayList<Integer> arrayWidthColumns = new ArrayList<Integer>(Arrays.asList(arrayWidth));
		ArrayList<String> arrayTitleColumns = new ArrayList<String>(Arrays.asList(arrayTitle));
		TableConsole table = new TableConsole(arrayWidthColumns);
		// set title
		table.setTitle(arrayTitleColumns);

      	for(int i = 0; i < this.listElement.size(); ++i) {
			ArrayList<String> rowData = new ArrayList<String>();
			Product el = this.listElement.get(i);

			rowData.add(Integer.toString(i + 1)); // add so thu tu

			rowData.addAll(el.xuatMangThongTin());
			
			String xuatLoaiSanPham = (el.getClass().getSimpleName().equals("FastFood")) ? "Thuc an nhanh" : "Thuc uong";
			rowData.add(xuatLoaiSanPham);

			table.addRowData(rowData);
      	}

		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "DANH SACH SAN PHAM", '-'));
		table.DrawTable();
   	}

   	@Override
   	public int size() {
      	return this.listElement.size();
   	}

   	@Override
   	public boolean contains(String strProperty) {
        for(Product el : listElement) {
            if(el.contains(strProperty)) {
                return true;
            }
        }

        return false;
   	}

   	@Override
	public int indexOf(String strProperty) {
		for(int i = 0; i < this.listElement.size(); ++i) {
			Product el = this.listElement.get(i);
			
			if(el.contains(strProperty)) {
				return i;
			}
		}

		return -1;
	}

   	@Override
   	public boolean removeElement(int index) {
		return (this.listElement.remove(index) != null)? true : false;
   	}

   	@Override
   	public void readListDataInDatabase(ArrayList<String> collection) {
       	for(String data : collection) {
        	Product pt = this.ParseTypeData(data); 
           	this.listElement.add(pt);
       	}
   	}

   	private Product ParseTypeData(String data) {
      	String typeData = data.substring(0, 2);
      	String contentData = data.substring(2);
    
		Product pt = null;
      	switch(typeData) {
        	case "FF":
				pt = (Product)new FastFood();
				break;
        	case "DS":
				pt = (Product)new Drinks();
				break;
      	}

		pt.readDataInDatabase(contentData);
		return pt;
   	}

   	@Override
   	public ArrayList<String> writeListDataInDatabase() {
      	ArrayList<String> collection = new ArrayList<String>();

      	for(Product el : listElement) {
           	collection.add(addTypeToData(el));
      	}

      	return collection;
   	}

 	private String addTypeToData(Product pt) {
        String typeData = pt.getClass().getSimpleName();
        IFile iF = (IFile)pt;
        String data = "";
        
        switch(typeData) {
            case "FastFood": 
                data = "FF";
                break;
            case "Drinks":
                data = "DS";
                break;
        }
		data += iF.writeDataInDatabase();
        return data;
    }
}
