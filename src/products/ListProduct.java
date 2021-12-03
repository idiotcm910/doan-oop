package products;

import java.util.ArrayList;
import lib.IFile;
import lib.IListFile;
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
      for(int i = 0; i < this.listElement.size(); ++i) {
         System.out.printf("%5s%-8s%s%5s\n", " ", i + 1, this.listElement.get(i).xuatThongTin(), " ");
      }
   }

   @Override
   public int size() {
      return this.listElement.size();
   }

   @Override
   public boolean containString(String strProperty) {
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
   public Object findElement(String strProperty) {
       for(Product el : listElement) {
            if(el.contains(strProperty)) {
                return el;
            }
       } 
       return null;
   }

   @Override
   public int remove(Object obj) {
      for(int i = 0; i < this.listElement.size(); ++i) {
         if ((this.listElement.get(i)).equals(obj)) {
            this.listElement.remove(i);
            return 1;
         }
      }

      return -1;
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
        case "DS":
            FastFood ff = new FastFood();
            ff.readDataInDatabase(contentData);
            return ff;
        case "FF":
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
