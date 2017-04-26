/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author kiett
 */
public class ManageStaffModel {

    private void copyImg(String linkImg, String linkSave) {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {

            File rootfile = new File(linkImg);
            File copyfile = new File("C:\\Images\\" + linkSave);

            inStream = new FileInputStream(rootfile);
            outStream = new FileOutputStream(copyfile);

            byte[] buffer = new byte[1024];

            int length;
            // Copy the file content in bytes.
            // Sao chep nội dung tập tin dưới dạng bytes.
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File copy is successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getDirectoryProject() {
        try {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            path = path.substring(0, path.length() - 1);
            return path;
        } catch (Exception e) {
            return e.toString();
        }
    }
}
