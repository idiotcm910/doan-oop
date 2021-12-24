import menu.AppHeader;
import menu.DMenu;

import utils.Display;
import lib.IHienThi;

public class Main {
	public static void main(String[] args) {
		Display dp = Display.getInstance();
		dp.setHeader((IHienThi)new AppHeader());

		dp.hienThi((IHienThi)new DMenu());
	}
}



