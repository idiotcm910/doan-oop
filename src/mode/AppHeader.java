package mode;

import lib.IHienThi;

public class AppHeader implements IHienThi
{
    public void xuatTitle() {
    }
    
    public void xuat() {
        System.out.printf("%s\n", String.format("%105s", "=").replaceAll(" ", "="));
        System.out.printf("|%103s|\n", " ");
        System.out.printf("|%103s|\n", " ");
        System.out.printf("|%31s%s%31s|\n", " ", "QUAN LY CUA HANG THUC AN NHANH VA DO UONG", " ");
        System.out.printf("|%103s|\n", " ");
        System.out.printf("|%103s|\n", " ");
        System.out.printf("%s\n", String.format("%105s", "=").replaceAll(" ", "="));
        System.out.printf("\n\n\n", new Object[0]);
    }
}
