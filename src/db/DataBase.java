package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DataBase {
   private String pathCollection;

   public DataBase(String var1) {
      this.pathCollection = "./db/data/" + var1 + ".txt";
   }

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
}

