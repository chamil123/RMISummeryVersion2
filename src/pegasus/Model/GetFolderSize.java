/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pegasus.Model;

import java.io.File;

public class GetFolderSize {

    private static String kilobytes1;
    private static String megabytes2;
    private static String gigabytes3;
    int totalFolder = 0;
    int totalFile = 0;
    static int fileQty;

    public static String size(String i) {

        String folder = "C:/Sankalpa/summery.01";
        GetFolderSize size = new GetFolderSize();
        long fileSizeByte = size.getFileSize(new File(folder));
        double kilobytes = (fileSizeByte / 1024);
        double megabytes0 = Math.round(kilobytes / 1024);
        double megabytes = (kilobytes / 1024);
        double gigabytes0 = Math.round(megabytes / 1024);
        double gigabytes = (megabytes / 1024);

        fileQty = size.getTotalFile();
        GetFolderSize gfs = new GetFolderSize();
        gfs.m(fileQty);

        StringBuffer kb0 = null;
        if (gigabytes0 != 0.0) {
            kb0 = new StringBuffer(gigabytes + "");

        } else if (megabytes0 != 0.0) {
            kb0 = new StringBuffer(megabytes + "");

        } else {
            kb0 = new StringBuffer(kilobytes + "");

        }
        int index1 = kb0.indexOf(".");
        String kb1 = kb0.substring(0, index1);
        String kb2 = kb0.substring(index1 + 1);
        String kb3[] = kb2.split("");

        if (gigabytes0 != 0.0) {
            gigabytes3 = kb1 + "." + kb3[1] + "" + kb3[2] + " GB";
            return gigabytes3;

        } else if (megabytes0 != 0.0) {
            megabytes2 = kb1 + "." + kb3[1] + " MB";
            return megabytes2;

        } else {
            kilobytes1 = kb1 + "." + kb3[1] + " KB";
            return kilobytes1;

        }
    }

    public static String m(int qty) {
        String QTY = fileQty + " Backup(s)";
        return QTY;
    }

    public long getFileSize(File folder) {
        long foldersize = 0;
        try {
            totalFolder++;

            File[] filelist = folder.listFiles();
            for (int i = 0; i < filelist.length; i++) {
                if (filelist[i].isDirectory()) {
                    foldersize += getFileSize(filelist[i]);
                } else {
                    totalFile++;
                    foldersize += filelist[i].length();
                }
            }

        } catch (NullPointerException e) {
        } catch (Exception e) {
        }
        return foldersize;
    }

    public int getTotalFolder() {
        return totalFolder;
    }

    public int getTotalFile() {
        return totalFile;
    }
}
