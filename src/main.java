import mode.AppHeader;
import mode.DMenu;
import utils.Display;
import mode.dsfastfood.DMenuDSFF;

import java.util.ArrayList;

import db.DataBase;
import products.FastFood;
public class test {
	public static void main(String[] args) {
        Display dp = Display.getInstance();

        dp.setHeader(new AppHeader());
        dp.hienThi(new DMenu());
	}

}



