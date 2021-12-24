package lib;

public interface IFile
{
	/**
	 *	Hàm này sẽ nhận về chuỗi dữ liệu từ file và sẽ xứ lý dữ liệu này thành
	 *	các thuộc tính của đối tượng.
	 *
	 *	Dữ liệu trong file được tổ chức theo cấu trúc mỗi hàng trong file là một hàng dữ liệu
	 *	các dữ liệu con trong một hàng được phân theo dấu ; 
 	 *	Ví dụ: LeQuocThai;01/09/2002;19
     *	,nếu dữ liệu con trong một hàng là một mảng các dữ liệu thì sẽ được phân theo dấu :
 	 *	Ví dụ: LeQuocThai;01/09/2002;1:2:3:4
 	 *	,nếu các dữ liệu trong mảng dữ liệu con trong một hàng lại là mảng thì được phân theo dấu \
	 *	Ví dụ: Combo A;1\Hamburger:6\coca-cola:7\pesi;40;5
	 *	@param String
	 * */
    void readDataInDatabase(String data);
    
	/**
	 *	Hàm này sẽ chuyễn các thuộc tính của đối tượng thành chuỗi dữ liệu theo cấu trúc lưu dữ liệu trong file.
	 *
	 *	Dữ liệu trong file được tổ chức theo cấu trúc mỗi hàng trong file là một hàng dữ liệu
	 *	các dữ liệu con trong một hàng được phân theo dấu ; 
 	 *	Ví dụ: LeQuocThai;01/09/2002;19
     *	,nếu dữ liệu con trong một hàng là một mảng các dữ liệu thì sẽ được phân theo dấu :
 	 *	Ví dụ: LeQuocThai;01/09/2002;1:2:3:4
 	 *	,Nếu các dữ liệu trong mảng dữ liệu con trong một hàng lại là mảng thì được phân theo dấu \
	 *	Ví dụ: Combo A;1\Hamburger:6\coca-cola:7\pesi;40;5
	 *	@return string
	 * */
    String writeDataInDatabase();
}
