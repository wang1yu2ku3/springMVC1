package com.wyl.test.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadUtil {
	
	private final static String audio_path="D:\\words_dict\\";
	
	private final static String audio_url = "http://audio.dict.cn/";
	
	public static String downAudio(String uri,String word,String fileType) throws MalformedURLException, IOException{
		InputStream in=new URL(audio_url + uri.replace(" ", "%20")).openConnection().getInputStream();
		String path = audio_path + word + "_" + fileType + ".mp3";
		FileOutputStream f = new FileOutputStream(path);
		byte [] bb=new byte[1024];  //接收缓存
		int len;
		while( (len=in.read(bb))>0){ //接收
		  f.write(bb, 0, len);  //写入文件
		}
		f.close();
		in.close();
		return path;
	}

}
