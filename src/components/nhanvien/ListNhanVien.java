package components.nhanvien;

import lib.IFile;
import java.util.ArrayList;
import java.util.Arrays;

import lib.IListFile;
import utils.DisplayFormat;
import utils.TableConsole;
import utils.Validation;
import lib.IListElement;

public class ListNhanVien implements IListFile, IListElement
{
    public ArrayList<NhanVien> listnhanvien;
    
    public ListNhanVien() {
        this.listnhanvien = new ArrayList<NhanVien>();
    }
    
	@Override
    public boolean addElement(Object obj) {
		NhanVien nv = (NhanVien)obj;
		return this.listnhanvien.add(nv);
    }
    
    public void addAllElement(ListNhanVien list) {
        for (int i = 0; i < list.size(); ++i) {
            this.listnhanvien.add(list.getNhanvien(i));
        }
    }
    public NhanVien getElement(int index) {
      	return this.listnhanvien.get(index);
   	}

	@Override
    public boolean setElement(int index, Object obj) {
		NhanVien nv = (NhanVien)obj;
		if(this.listnhanvien.set(index, nv) == null) {
			return false;
		}
		return true;
   	}
        
    public NhanVien getNhanvien(int index) {
        return this.listnhanvien.get(index);
    }
    
    public void setNhanVien(int index, NhanVien nv) {
        this.listnhanvien.set(index, nv);
    }
    
	@Override
	public void nhapDanhSachThongTin() {
        System.out.println("Nhap so luong nhan vien can them: ");
        int a = Validation.nhapDuLieuSo(0, 1000);
        for (int i = 1; i <= a; ++i) {
            NhanVien nv = new NhanVien();
            System.out.println ("---------------- Nhap thong tin nhan vien -----------------");
            nv.nhap();
            this.addElement(nv);
        }
	}

    @Override
    public void xuatDanhSachThongTin() {
		Integer[] arrayWidth = {6, 23, 15, 13, 13, 13, 7, 16, 15, 10};
		String[] arrayTitle = {"STT", "Ho Va Ten", "Gioi Tinh", "Ngay Sinh", "SDT", "CMND", "Ma", "Ngay Vao Lam", "Vi Tri", "Luong"};
		ArrayList<Integer> arrayWidthColumns = new ArrayList<Integer>(Arrays.asList(arrayWidth));
		ArrayList<String> arrayTitleColumns = new ArrayList<String>(Arrays.asList(arrayTitle));
		TableConsole table = new TableConsole(arrayWidthColumns);
		table.setTitle(arrayTitleColumns);
      	for(int i = 0; i < this.listnhanvien.size(); ++i) {
			ArrayList<String> rowData = new ArrayList<String>();
			rowData.add(Integer.toString(i + 1)); // add so thu tu
			rowData.addAll(this.listnhanvien.get(i).xuatThongTin());
			table.addRowData(rowData);
      	}
		System.out.println(DisplayFormat.inRaChuNamGiua(DisplayFormat.widthDisplay, "DANH SACH NHAN VIEN", '-'));
		table.DrawTable();
    }
    
    @Override
    public int size() {
        return this.listnhanvien.size();
    }

	@Override
	public boolean isEmpty() {
		return this.listnhanvien.isEmpty();
	}
    
    @Override
    public boolean contains(String strProperty) {
        for (NhanVien nv : this.listnhanvien) {
            if (nv.contains(strProperty)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int indexOf(String strProperty) {
        for (int i = 0; i < this.listnhanvien.size(); ++i) {
            NhanVien nv = this.listnhanvien.get(i);
            if (nv.contains(strProperty)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public boolean removeElement(int index) {
        if (index < 0 || index >= this.listnhanvien.size()) {
        	return false;
        }
        this.listnhanvien.remove(index);
        return true;
    }
    
    @Override
    public void readListDataInDatabase(ArrayList<String> listnhanvien) {
        for (int i = 0; i < listnhanvien.size(); ++i) {
            NhanVien nv = new NhanVien();
            nv.readDataInDatabase(listnhanvien.get(i));
            this.listnhanvien.add(nv);
        }
    }
    
    @Override
    public ArrayList<String> writeListDataInDatabase() {
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < this.listnhanvien.size(); ++i) {
            IFile el = (IFile)this.listnhanvien.get(i);
            data.add(el.writeDataInDatabase());
        }
        return data;
    }
}
