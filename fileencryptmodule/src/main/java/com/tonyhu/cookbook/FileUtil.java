package com.tonyhu.cookbook;

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
            int len = in.read(tempbytes);
            while (len != -1) {//简单的交换
                byte a = tempbytes[0];
                tempbytes[0] = tempbytes[1];
                tempbytes[1] = a;
                out.write(tempbytes,0,len);//写文件
                len = in.read(tempbytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) throws Exception{
        String path = "E:\\Project\\workspace\\LoveFood\\app\\src\\main\\assets";
//        String path = "F:\\workspace\\LoveFood\\app\\src\\main\\assets";
        File directory = new File(path);
        if(!directory.exists()) {
            throw new Exception("path:'" + path + "' does not exist,r u kidding.");
        }
        File[] subFiles = directory.listFiles();
        //删除旧目录
        for(File file: subFiles) {
            if(file.getName().contains("_new")) {
                deleteDir(file);
            }
        }
        subFiles = directory.listFiles();
        //建目标目录
        for(File file: subFiles) {
            File newFile =  new File(file.getAbsolutePath() + "_new");
            newFile.mkdirs();
            copyFolder(file,newFile);
        }
        System.out.println("Job done.");
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

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
