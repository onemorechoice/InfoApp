package com.news.ye.newsdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YU on 2016/6/28.
 */
public class DateFormatUtil {
    public static String formatDate(String createTime){
        String time = "unknown date";
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date date=inputFormat.parse(createTime);
            time=outputFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
            time="unknown date";
        }
        return time;
    }
}
