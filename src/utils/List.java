package utils;


abstract public class List {
  	protected abstract int size();
  	protected abstract void xuatDanhSachThongTin();

	// ====== Các hàm tìm kiếm ======
   	// hàm này sẽ kiểm tra xem một chuỗi có phải là 1 thuộc tính của một đối tượng
   	// thuộc danh sách không.
  	protected abstract boolean contains(String strProperty);
	
	// hàm này sẽ kiểm tra xem đối tượng có thuộc danh sách không.
	protected abstract boolean contains(Object obj);
	
	// hàm tìm kiếm phần tử dựa trên đối tượng truyền vào 
	// tìm thấy sẽ trả về chỉ mục của phần tử đó
	// không tìm thấy trả về -1
	protected abstract int indexOf(Object obj);

	// hàm này sẽ kiểm tra xem một chuỗi có phải là 1 thuộc tính của phần tử trong danh sách không 
	// nếu có thì trả về chỉ mục của phần tử đó, không trả về -1
	protected abstract int indexOf(String strProperty);

  	// hàm xóa phần tử.
  	protected abstract int remove(int index);
}
