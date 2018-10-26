package cn.bingoogolapple.bgabanner.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian.shui on 2018/10/26
 */
public class DataUtils {

    public static List<String> getImgs(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<4;i++){
            list.add("http://pic19.nipic.com/20120308/4970979_102637717125_2.jpg");
        }
        return  list;
    }

    public static List<String> getTips(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<4;i++){
            list.add("title"+i);
        }
        return  list;
    }
}
