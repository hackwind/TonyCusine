package com.tonyhu.cookbook.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/5/22.
 */

public class FileUtil {

    /**
     tempbytes[0] = tempbytes[1];
     * 对文件进行加密
     * @param dest
     */
    public static void encrypt(File src,File dest){
        byte[] tempbytes = new byte[5000];
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            while (in.read(tempbytes) != -1) {//简单的交换
                byte a = tempbytes[0];
                tempbytes[0] = tempbytes[1];
                tempbytes[1] = a;
                out.write(tempbytes);//写文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) throws Exception{
        String path = "E:\\Project\\workspace\\LoveFood\\app\\src\\main\\assets";
        File directory = new File(path);
        if(!directory.exists()) {
            throw new Exception("path:'" + path + "' does not exist,r u kidding.");
        }
        File[] subFiles = directory.listFiles();

        //建目标目录
        for(File file: subFiles) {
            File newFile =  new File(file.getAbsolutePath() + "_new");
            newFile.mkdirs();
            copyFolder(file,newFile);
        }
    }

    private static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // 递归复制
                copyFolder(srcFile, destFile);
            }
        } else {
            encrypt(src,dest);
        }
    }
}
