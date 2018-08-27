package com.wyl.test.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static void main(String[] args) throws Exception {
		/*
		String str = new String("amigoxiexiexingxing");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
        */
		
		MD5Utils md5Util=new MD5Utils();  
        String result=md5Util.getMD5("abc".getBytes());  
        System.out.println(result);
	}
	
	 /*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    public String getMD5(byte[] source){  
        String s=null;  
        //用来将字节转换成16进制表示的字符  
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9',  
                'a','b','c','d','e','f'};  
        try {  
            MessageDigest md=MessageDigest.getInstance("MD5");  
            md.update(source);  
            //MD5的计算结果是一个128位的长整数，用字节表示为16个字节  
            byte[] tmp=md.digest();  
            //每个字节用16进制表示的话，使用2个字符(高4位一个,低4位一个)，所以表示成16进制需要32个字符  
            char[] str=new char[16*2];  
            int k=0;//转换结果中对应的字符位置  
            for(int i=0;i<16;i++){//对MD5的每一个字节转换成16进制字符  
                byte byte0=tmp[i];  
                str[k++]=hexDigits[byte0>>>4 & 0xf];//对字节高4位进行16进制转换  
                str[k++]=hexDigits[byte0 & 0xf];    //对字节低4位进行16进制转换  
            }  
            s=new String(str);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return s;  
    }

}
