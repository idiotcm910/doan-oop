package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
 *	Dữ liệu trong file được tổ chức theo cấu trúc mỗi hàng trong file là một hàng dữ liệu
 *	các dữ liệu con trong một hàng được phân theo dấu ; 
 *	Ví dụ: LeQuocThai;01/09/2002;19
 *
 *	nếu dữ liệu con trong một hàng là một mảng các dữ liệu thì sẽ được phân theo dấu :
 *	Ví dụ: LeQuocThai;01/09/2002;1:2:3:4
 *
 *	Nếu các dữ liệu trong mảng dữ liệu con trong một hàng lại là mảng thì được phân theo dấu \
 *	Ví dụ: Combo A;1\Hamburger:6\coca-cola:7\pesi;40;5
 *
 */

public class DataBase {
   private String pathCollection;

   public DataBase(String var1) {
      	this.pathCollection = "./data/" + var1 + ".txt";

		//check file da ton tai chua, neu chua thi tao file moi
		try {
			File f = new File(this.pathCollection);
			if(!f.exists()) {
				f.createNewFile();
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
   }

   /**
	*  Hàm này sẽ lấy toàn bộ dữ liệu trong file
	*  @return mảng ArrayList String dữ liệu đã lấy trong file
	* */
   public ArrayList<String> find() {
      ArrayList<String> collection = new ArrayList<String>();

      try {
         FileReader fr = new FileReader(this.pathCollection);
         BufferedReader br = new BufferedReader(fr);

         String data;
         while((data = br.readLine()) != null) {
            collection.add(data);
         }

         br.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return collection;
   }

	
   /**
	*  Hàm này sẽ cập nhật toàn bộ dữ liệu trong file
	*  @param ArrayList<String>: Mảng các dữ liệu mới
	* */
   public void update(ArrayList<String> collection) {
      try {
         FileWriter fw = new FileWriter(this.pathCollection);
         BufferedWriter bw = new BufferedWriter(fw);
        
         for(String data : collection) {
            bw.write(data);
            bw.newLine();
         }
         bw.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
	*  Hàm này sẽ tìm nhiều dữ liễu trong file dựa vào mảng chuỗi điều kiện truyền vào.
	*  @param ArrayList<String>: Mảng chuỗi điều kiện
	*  @return mảng ArrayList chuỗi dữ liệu được tìm thấy
	* */
   public ArrayList<String> findMany(ArrayList<String> condition) {
		ArrayList<String> collection = new ArrayList<String>();	

		try {
			FileReader fr = new FileReader(this.pathCollection);
			BufferedReader br = new BufferedReader(fr);	

			br.mark(1000);
			for(int i = 0; i < condition.size(); ++i) {
				String dataCodition = condition.get(i);

				String dataFile;
				while((dataFile = br.readLine()) != null) {
					if(dataFile.contains(dataCodition) && !collection.contains(dataFile)) {
						collection.add(dataFile);
					}
				}

				br.reset();
			}

			br.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

		return collection;
   }


   /**
	* Hàm này sẽ tìm dữ liệu trong file dựa vào chuỗi điều kiện truyền vào.
	* @param String 
	* @return String, nếu tìm được dữ liệu có chuỗi điều kiện thuộc dữ liệu đó thì trả về chuỗi dữ liệu, không thì trả về String ""
	* */
   public String findOne(String condition) {
		String collection = ""; 

		try {
			FileReader fr = new FileReader(this.pathCollection);
			BufferedReader br = new BufferedReader(fr);

			String data;	
			while((data = br.readLine()) != null) {
				if(data.contains(condition)) {
					collection = data;
					break;
				}
			}

			br.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

		return collection;
   }


   /**
	* Hàm này sẽ tìm những dữ liệu có chứa chuỗi kí tự đối số truyền vào và xóa những dữ liệu đó đi.
	* @param String
	* @return true, nếu xóa dữ liệu thành công
	* */
   public boolean findAndDelete(String condition) {
		boolean isSuccess = false;
		ArrayList<String> collection = this.find();
		ArrayList<Integer> listIndexElement = new ArrayList<Integer>();

		// Tìm chỉ mục của phần tử cần xóa trong collection
		for(int i = 0; i < collection.size(); ++i) {
			String data = collection.get(i);	
			if(data.contains(condition)) {
				listIndexElement.add(i);
			}
		}
		
		for(int index : listIndexElement) {
			collection.remove(index);
		}

		this.update(collection);

		return isSuccess;
   }

   /**
	* Hàm này sẽ tìm những chuỗi kí tự trùng với chuỗi kí tự đối số truyền vào và thay thế tất cả chuỗi đó bằng một chuỗi kí tự mới.
	* @param String: Chuỗi kí tự điều kiện 
	* @param String: String: chuỗi kí tự thay thế
	* @return true, nếu thay thế thành công
	* */
   public boolean findAndUpdate(String condition, String newString) {
		boolean isSuccess = false;
		ArrayList<String> collection = this.find();
		
		for(int i = 0; i < collection.size(); ++i) {
			String oldData = collection.get(i);

			if(oldData.contains(condition)) {
				String newData = oldData.replaceAll(condition, newString);
				collection.set(i, newData);
				isSuccess = true;
			}
		}

		this.update(collection);

		return isSuccess;
   }
}
