package com.example.esdemo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReadCSV {
    public static List<List<String>> readCSV(String filePath, boolean hasTitle, String encoding){
        List<List<String>> data=new ArrayList<>();
        String line=null;
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),encoding));
            if (hasTitle){
                //第一行信息，为标题信息
                line = bufferedReader.readLine();
                String[] items=line.split(",");
                data.add(Arrays.asList(items));
//                System.out.println("标题行："+line);
            }

            int i=0;
            while((line=bufferedReader.readLine())!=null){
                i++;
                //数据行
                String[] items=line.split(",");
                data.add(Arrays.asList(items));
//                System.out.println("第"+i+"行："+line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

//    public static void main(String[] args) {
//        List<List<String>> list = ReadCSV.readCSV("src/main/resources/movie_cbooo.csv",true,"UTF-8");
//        for( int i = 0; i < 5; i++){
//            for(String string : list.get(i)){
//                System.out.printf(string+" ");
//            }
//            System.out.println();
//        }
//    }
}
