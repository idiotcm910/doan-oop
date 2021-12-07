package products;

import java.util.ArrayList;
import lib.IFile;
import lib.IListFile;
import utils.DisplayFormat;
import utils.List;

public class ListProduct extends List implements IListFile {
   	private ArrayList<Product> listElement = new ArrayList<Product>();

   	public void addElement(Product pt) {
      	this.listElement.add(pt);
   	}

   	public void addAllElement(ListProduct list) {
      	for(int i = 0; i < list.size(); ++i) {
         	this.listElement.add(list.getElement(i));
      	}

   	}

   	public Product getElement(int index) {
      	return this.listElement.get(index);
   	}

   	public void setElement(int index, Product pt) {
      	this.listElement.set(index, pt);
   	}

   	@Override
   	public void xuatDanhSachThongTin() {
		System.out.println(DisplayFormat.inRaHangCungKyTu(DisplayFormat.getWidthDisplay(), '='));
	    System.out.printf("%-5s%-8s%-10s%-25s%-11s%-16s%-25s%-16s%5s\n",
			" ", "STT", "MaSP", "Ten San Pham", "gia", "hldd", "ngay nhap", "Loai san pham", " ");
		System.out.println(DisplayFormat.inRaHangCungKyTu(DisplayFormat.getWidthDisplay(), '='));

      	for(int i = 0; i < this.listElement.size(); ++i) {
			Product el = this.listElement.get(i);
			String xuatLoaiSanPham = (el.getClass().getSimpleName().equals("FastFood")) ? "Thuc an nhanh" : "Thuc uong";

         	System.out.printf("%5s%-8s%s%-16s%5s\n", " ", i + 1, this.listElement.get(i).xuatThongTin(), xuatLoaiSanPham, " ");
      	}
		
		System.out.println(DisplayFormat.inRaHangCungKyTu(DisplayFormat.getWidthDisplay(), '='));
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
   	public boolean contains(Object obj) {
        for(Product el : listElement) {
            if(el.equals(obj)) {
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
	public int indexOf(Object obj) {
		for(int i = 0; i < this.listElement.size(); ++i) {
			Product el = this.listElement.get(i);

			if(el.equals(obj)) {
				return i;
			}
		}

		return -1;
	}

   	@Override
   	public int remove(int index) {
		if(index < 0 || index >= this.listElement.size()) { return -1; }

		this.listElement.remove(index);
		return 1;
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
    
      	switch(typeData) {
        	case "FF":
            	FastFood ff = new FastFood();
            	ff.readDataInDatabase(contentData);
            	return ff;
        	case "DS":
            	Drinks ds = new Drinks();
            	ds.readDataInDatabase(contentData);
            return ds;
      	}

        return null;
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
                data = "FF" + iF.writeDataInDatabase();
                break;
            case "Drinks":
                data = "DS" + iF.writeDataInDatabase();
                break;
        }
        return data;
    }
}
