package com.wyl.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegHtml {

	public static void main(String[] args) {
		//<a title=中国体育报 href=''>aaa</a><a title='北京日报' href=''>bbb</a>"
		String source = "<i class=\"sound\" naudio=\"mbTd30ZA8fef96fe87733b5275966b5aca59d549.mp3?t=a\" title=\"男生版发音\"/>";
		List<String> list = match(source, "i", "naudio");  
        System.out.println(list);
	}
	
	public static List<String> match(String source, String element, String attr) {  
        List<String> result = new ArrayList<String>();
        
        //String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
        String re1=".*?";	// Non-greedy match on filler
	    String re2="\".*?\"";	// Uninteresting: string
	    String re3=".*?";	// Non-greedy match on filler
	    String re4="(\".*?\")";	// Double Quote String 1

	    Pattern p = Pattern.compile(re1+re2+re3+re4,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(source);  
        while (m.find()) {  
            String r = m.group(1);
            result.add(r.substring(1, r.length()-1));
        }  
        return result;  
    }

}
