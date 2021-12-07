package utils;

import lib.InputException;
import java.util.Scanner;
import lib.IHienThi;

public class Display
{
    private static Display instance = null;
    private IHienThi dHeader;
    
    private Display() {
        this.dHeader = null;
    }
    
    public static Display getInstance() {
        if (Display.instance == null) {
            Display.instance = new Display();
        }
        return Display.instance;
    }
    
    private void xoaManHinh() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }
    
    private void xuLyLoi(String x) {
        System.out.println(x);
        System.out.println("Nhan nut bat ky de tiep tuc!!!");
        new Scanner(System.in).nextLine();
    }
    
    public void setHeader(IHienThi dHeader) {
        this.dHeader = dHeader;
    }
    
    public void hienThi(IHienThi hienThi) {
		while(true) {
	        try {
	            this.xoaManHinh();
    	        if (this.dHeader != null) {
        	        this.dHeader.xuat();
            	}
            	hienThi.xuatTitle();
            	hienThi.xuat();
        	}
        	catch (InputException ex) {
            	this.xuLyLoi(ex.getMessage());
        	}
    	}
	}

}
