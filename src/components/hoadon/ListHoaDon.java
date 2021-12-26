package components.hoadon;

import java.util.ArrayList;
import java.util.Arrays;

import lib.IListFile;
import lib.IListElement;
import utils.DisplayFormat;
import utils.TableConsole;
import lib.InputException;
import utils.Validation;
 public class ListHoaDon implements IListElement, IListFile {

	private ArrayList<HoaDon> listHoaDon;

	public ListHoaDon() {
		this.listHoaDon = new ArrayList<HoaDon>();
	}
        
    public boolean addElement(Object obj) {
		HoaDon hd = (HoaDon)obj;
      	return this.listHoaDon.add(hd);
   	}
        
        
        public void addAllHoaDon(ListHoaDon list){
            for(int i = 0; i<list.size();++i){
                this.listHoaDon.add(list.getHoaDon(i));
            }
            
        }
        
        public HoaDon getHoaDon(int index){
            return this.listHoaDon.get(index);
        }
        
        public boolean setElement(int index, Object obj){
			HoaDon hd = (HoaDon)obj;
            if(this.listHoaDon.set(index, hd) != null) {
				return true;
			}
			return false;
        }

	@Override
	public void nhapDanhSachThongTin() {
	    System.out.print("Nhap so luong hoa don can them: ");
        int a = Validation.nhapDuLieuSo(1, 100);

		for (int i = 1; i <= a; ++i) {
			try {
	            HoaDon hd = new HoaDon();
		      	System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay / 2, "Nhap thong tin hoa don thu " + i, '='));
            	hd.nhap();
            
	            boolean isEqualsmaHoaDon = this.contains(hd.getmaHoaDon());
            
    	        if(isEqualsmaHoaDon) {
						throw new InputException("Ma hoa don da co, vui long nhap lai du lieu!!!");
				}
            	this.addElement(hd);
			}
			catch(InputException ex) {
				System.out.println(ex.getMessage());
				i -= 1;
			}
        }
	}
        
    @Override
   	public void xuatDanhSachThongTin() {
		Integer[] arrayWidth = {8, 15, 100, 11, 24};
		String[] arrayTitle = {"STT", "Ma hoa don", "Danh sach san pham", "Gia", "Ngay nhap thong tin"};
		// tao bang
		ArrayList<Integer> arrayWidthColumns = new ArrayList<Integer>(Arrays.asList(arrayWidth));
		ArrayList<String> arrayTitleColumns = new ArrayList<String>(Arrays.asList(arrayTitle));
		TableConsole table = new TableConsole(arrayWidthColumns);
		table.setTitle(arrayTitleColumns);
		// them hang du lieu vao bang
		for(int i = 0; i < this.listHoaDon.size(); ++i) {
			HoaDon hd = this.listHoaDon.get(i);
			ArrayList<String> hangDuLieu = new ArrayList<String>();
			hangDuLieu.add(Integer.toString(i + 1)); // them so thu tu
			hangDuLieu.addAll(hd.xuatMangThongTin());
			table.addRowData(hangDuLieu);
		}
		// in ra bang du lieu trong console
		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "DANH SACH HOA DON", '-'));
		table.DrawTable();	
   	}
        
		@Override
		public boolean isEmpty() {
			return this.listHoaDon.isEmpty();
		}

        @Override
        public int size(){
            return this.listHoaDon.size();
        }
        
        @Override
   	public boolean contains(String strProperty) {
        for(HoaDon hd : listHoaDon) {
            if(hd.contains(strProperty)) {
                return true;
            }
        }
        return false;
        }
    
        @Override
	public int indexOf(String strProperty) {
		for(int i = 0; i < this.listHoaDon.size(); ++i) {
			HoaDon el = this.listHoaDon.get(i);
			if(el.contains(strProperty)) {
				return i;
			}
		}

		return -1;
	}
        
        @Override
   	public boolean removeElement(int index) {
		if(index < 0 || index >= this.listHoaDon.size())
                { return false; }
		this.listHoaDon.remove(index);
		return true;
   	}
        
        
        @Override
   	public void readListDataInDatabase(ArrayList<String> collection) {
       	for(String data : collection) {
			HoaDon hd = new HoaDon();
			hd.readDataInDatabase(data);
           	this.listHoaDon.add(hd);
       	}
   	}
        
        
       @Override
   	public ArrayList<String> writeListDataInDatabase() {
      	ArrayList<String> collection = new ArrayList<String>();
      	for(HoaDon hd : listHoaDon) {
           	collection.add(hd.writeDataInDatabase());
      	}
      	return collection;
   	}
        
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    
    
        
        
       
