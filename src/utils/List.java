package utils;


abstract public class List {
  protected abstract int size();
  protected abstract void xuatDanhSachThongTin();

   // hàm này sẽ kiểm tra xem một chuỗi có phải là 1 thuộc tính của một đối tượng
   // thuộc danh sách không.
  protected abstract boolean containString(String strProperty);

   // hàm này sẽ kiểm tra xem đối tượng có thuộc danh sách không.
  protected abstract boolean contains(Object obj);

  // hàm này sẽ tìm kiếm xem chuỗi có phải là 1 thuộc tính của một đối tượng không,
  // nếu có trả về đối tượng đó
  // không trả về null
  protected abstract Object findElement(String strProperty);
  
  // hàm xóa phần tử, xóa thành công trả về 1, không thành công trả về -1
  protected abstract int remove(Object objRm);
}
