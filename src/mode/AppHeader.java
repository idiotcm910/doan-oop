package mode;

import lib.IHienThi;
import utils.DisplayFormat;

public class AppHeader implements IHienThi
{
    public void xuatTitle() {
    }
    
    public void xuat() {
		int widthDisplay = DisplayFormat.getWidthDisplay();

		System.out.println(DisplayFormat.inRaHangCungKyTu(widthDisplay, '='));

		System.out.println(DisplayFormat.inRaChuHaiBenHang(widthDisplay, '|'));
		System.out.println(DisplayFormat.inRaChuHaiBenHang(widthDisplay, '|'));

		System.out.printf("%s%s%s\n", "|", DisplayFormat.inRaChuNamGiua(widthDisplay - 2, "QUAN LY CUA HANG THUC AN NHANH VA DO UONG", ' '), "|");

		System.out.println(DisplayFormat.inRaChuHaiBenHang(widthDisplay, '|'));
		System.out.println(DisplayFormat.inRaChuHaiBenHang(widthDisplay, '|'));

		System.out.println(DisplayFormat.inRaHangCungKyTu(widthDisplay, '='));
        System.out.printf("\n\n\n");
    }
}
