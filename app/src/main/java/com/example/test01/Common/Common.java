package com.example.test01.Common;

public class Common {
    public static final String UPDATE="上傳";
    public static final String DELETE="刪除";

    public static int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "未接收訂單";
        else if(code.equals("1"))
            return "訂單準備中";
        else
            return "餐點已完成";
    }
}
