package lib;

/* 
 * class ngoại lệ xử lý các lỗi trong lúc người dùng nhập dữ liệu vào sai quy định hoặc gây lỗi
 * ví du: nhập bỏ trống hàng, nhập vượt quá kí tự quy định,....
 *
 * */

public class InputException extends RuntimeException
{
    public InputException(String message) {
        super(message);
    }
}
