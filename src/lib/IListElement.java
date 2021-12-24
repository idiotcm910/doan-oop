package lib;

public interface IListElement {
	/**
	 *	@return trả về số lượng phần tử của danh sách
	 * */
  	int size();

	/**
	 * Nhập danh sách thông tin
	 * */
	void nhapDanhSachThongTin();

	/**
	 *	Xuất ra console bảng danh sách dữ liệu
	 * */
  	void xuatDanhSachThongTin();

	/**
	 * Hàm kiểm tra xem danh sách hiện tại có đang trống dữ liệu không.
	 * @return true, nếu danh sách đang trống
	 * */
	boolean isEmpty();

	/**
	 *	Hàm thêm phần tử vào danh sách
	 *	@param Object 
	 *	@return true, nếu thêm phần tử vào danh sách thành công
	 * */
	boolean addElement(Object element);

	/**
	 *	Hàm sửa phần tử trong danh sách
	 *	@param index Chỉmục phần tử cần sửa
	 *	@param Object Đối tượng mới thay thế đối tượng cần sửa
	 *	@return true, nếu sửa phần tử thành công
	 * */
	boolean setElement(int index, Object element);

	/**
  	 * hàm xóa phần tử.
	 * @param int Đối số nhận vào chỉ mục của phần tử cần xóa
	 * @return true, nếu xóa phần tử thành công
	 * */
  	boolean removeElement(int index);

	// ====== Các hàm tìm kiếm ======
   	/** 
	 * hàm này sẽ kiểm tra xem một chuỗi có phải là một thuộc tính của một đối tượng thuộc danh sách không.
	 * @param String đối số nhận vào một đối tượng
	 * @return true, nếu chuỗi đối số là một thuộc tính của một đối tượng có trong danh sách
	 * */
  	boolean contains(String strProperty);
	
	/**
	 * hàm này sẽ kiểm tra xem một chuỗi có phải là một thuộc tính của phần tử trong danh sách không 
	 * @param String đối số nhận vào một chuỗi ký tự
	 * @return trả về chỉ mục của phần tử được tìm thấy, không tìm thấy trả về -1
	 * */
	int indexOf(String strProperty);
}
