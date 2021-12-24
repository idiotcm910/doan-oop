/*
 * ý tưởng là class sẽ có 1 mảng lưu kích thước của mỗi cột
 * vào sẽ xử lý tạo ra chuỗi định dạng hàng dữ liệu, và 2 hàng bao bảng
 * Mẫu thiết kế bảng:
 * +=====+============+===========+======+
 * | 123 | sadasdsa   | dasdasdd  | đa   |
 * +-----+------------+-----------+------+
 * |sdd  | dd         | dsdđ      | dsd  |
 * +-----+------------+-----------+------+
 * | đsđ | lequocthai | 232323    | 2323 |
 * +=====+============+===========+======+
 */

package utils;

import java.util.ArrayList;
import java.text.MessageFormat;

public class TableConsole {
	private ArrayList<Integer> arrayWidthColumns;
	private ArrayList<String> arrayTitleColumns;
	private ArrayList<ArrayList<String>> listRowData;
	private int numberColumns;
	
	private TableConsole() {
		this.arrayWidthColumns = new ArrayList<Integer>();
		this.arrayTitleColumns = new ArrayList<String>();
		this.listRowData = new ArrayList<ArrayList<String>>();
	}

	public TableConsole(ArrayList<Integer> arraywidthColumns) {
		this();
		this.arrayWidthColumns = arraywidthColumns;
		this.numberColumns = arraywidthColumns.size();
	}

	public void setTitle(ArrayList<String> arrayTitleColumns) {
		if(arrayTitleColumns.size() == numberColumns) {
			this.arrayTitleColumns = arrayTitleColumns;
		}
	}

	public void addRowData(ArrayList<String> rowData) {
		if(rowData.size() == numberColumns) {
			this.listRowData.add(rowData);
		}
	}

	private String chuoiHangDuLieu(ArrayList<String> listData) {
		String rowData = "";
		
		for(int i = 0; i < this.arrayWidthColumns.size(); ++i) {
			int widthColumns = this.arrayWidthColumns.get(i);
			String dataColumns = listData.get(i);

			String pattern = "| %-{0}s";
			String format = MessageFormat.format(pattern, widthColumns - 2);
			rowData += String.format(format, dataColumns);
		}
		
		return rowData.substring(0, rowData.length() - 1) + "|";
	}

	/*
	 *	Vẽ chuỗi hàng phân cách
	 *	+-------+-------+------+
	 *	+=======+=======+======+
	 * */
	private String chuoiHangPhanCach(char kyTuHang) {
		String hangPhanCach = "";

		for(int widthColumns : this.arrayWidthColumns) {
			String pattern = "+%{0}s";
			String format = MessageFormat.format(pattern, widthColumns - 1);

			hangPhanCach += String.format(format, DisplayFormat.inRaHangCungKyTu(widthColumns - 1, kyTuHang));
		}

		return hangPhanCach.substring(0, hangPhanCach.length() - 1) + "+";
	}

	public void DrawTable() {
		System.out.println(this.chuoiHangPhanCach('='));
		// ve title
		System.out.println(this.chuoiHangDuLieu(this.arrayTitleColumns));

		// ve du lieu
		for(ArrayList<String> rowData : this.listRowData) {
			System.out.println(this.chuoiHangPhanCach('-'));
			System.out.println(this.chuoiHangDuLieu(rowData));
		}

		System.out.println(this.chuoiHangPhanCach('='));
	}
}
