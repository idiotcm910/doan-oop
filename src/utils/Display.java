package utils;

import lib.InputException;
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
    
    private void xuLyLoi(String msg) {
        System.out.println(msg);
		DisplayFormat.dungChuongTrinh();
    }
    
    public void setHeader(IHienThi dHeader) {
        this.dHeader = dHeader;
    }
    
    public void hienThi(IHienThi hienThi) {
		boolean reset = true;
		while(reset) {
	        try {
	            this.xoaManHinh();
    	        if (this.dHeader != null) {
        	        this.dHeader.xuat();
            	}
            	hienThi.xuatTitle();
            	hienThi.xuat();

				reset = false;
        	}
        	catch (InputException ex) {
            	this.xuLyLoi(ex.getMessage());
				reset = true;
        	}
    	}
	}

}
