package com.example.lenovoz40.aww;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Ramon on 5/4/2016.
 */
public class FSUtil {
    public static boolean isStorageReady(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static String getStorage(){
        return Environment.getExternalStorageDirectory().toString()+"/Aww/";
    }
    public static void write(String filename, byte[] data){
        File fOutputDir = new File(getStorage());
        File fOutput = new File(getStorage(), filename);
        FileOutputStream fileOut = null;

        try{
            if (!fOutputDir.exists()){
                fOutputDir.mkdirs(); // create directory
            }
            if(!fOutput.exists()){
                fOutput.createNewFile();
            }
            fileOut = new FileOutputStream(fOutput, false);
            fileOut.write(data);
            fileOut.close();

        }
        catch(FileNotFoundException e){
            Log.e("ERROR", "File not found: " + fOutput.toString());
        }
        catch( Exception e){
            Log.e("ERROR", "Exception occured: " + e.getMessage());
        }
        return;
    }
    public static FileInputStream getFileInputStream(String filename){
        File fInput = new File(getStorage(), filename + ".txt");

        if(!fInput.exists()){ //check if file exists
            return null;
        }

        Log.i("INFO", "Accessing file: " +fInput.toString());
        Log.i("INFO", "     Exists: " + fInput.exists());

        FileInputStream fileIn = null;
        try{
            fileIn = new FileInputStream(fInput);
        }
        catch(FileNotFoundException e){
            Log.e("ERROR", "File not found: " + filename);
        }
        catch( Exception e){
            Log.e("ERROR","Exception occurred: "+e.getMessage());
        }
        return fileIn;
    }

/* public static String getFileData(String filename){
    FileInputStream fis=getFileInputStream(filename);
    String dataStr="";
    return;
}*/

}
