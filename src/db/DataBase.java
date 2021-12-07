package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;

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
			byte[] decodedArr = Base64.getDecoder().decode(data);
			String decodedData = new String(decodedArr);
            collection.add(decodedData);
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
			String encodedData = Base64.getEncoder().encodeToString(data.getBytes());
            bw.write(encodedData);
            bw.newLine();
         }
         bw.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}

