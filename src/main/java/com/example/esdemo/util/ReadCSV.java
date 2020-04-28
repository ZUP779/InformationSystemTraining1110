package com.example.esdemo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
//                String[] items=line.split(",");
//                data.add(Arrays.asList(items));
//                System.out.println("标题行："+line);
            }

            int i=0;
            while((line=bufferedReader.readLine())!=null){
//                String[] items=line.split(",");
//                data.add(Arrays.asList(items));
                data.add(cvsField(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    public static List<String> cvsField(String line){
        System.out.println(line);

        List<String> fields = new LinkedList<>();
        char[] alpah = line.toCharArray();
        boolean isFieldStart = true;
        int pos = 0; int len = 0;
        boolean yinhao = false;
        for(char c : alpah){
            if(isFieldStart){
                len = 0;
                isFieldStart = false;
            }
            if(c == '\"'){
                yinhao = !yinhao;
            }
            if(c == ',' && !yinhao){
                fields.add(new String(alpah, pos - len, len));
                isFieldStart = true;
            }
            pos++; len++;
        }
        fields.add(new String(alpah, pos - len, len));
        return fields;
    }

    public static void main(String[] args) {
        List<List<String>> list = ReadCSV.readCSV("src/main/resources/test.csv",true,"UTF-8");
        for( int i = 0; i < 5; i++){
            for(String string : list.get(i)){
                System.out.printf("%s --",string);
            }
            System.out.println();
        }
    }
}
