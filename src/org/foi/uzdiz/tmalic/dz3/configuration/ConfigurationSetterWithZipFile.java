/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tmalic.dz3.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.foi.uzdiz.tmalic.dz3.utils.Constants.Properties.PARAMETERS_FILE;

/**
 *
 * @author tadij
 */
public class ConfigurationSetterWithZipFile extends InitialConfiguration {

    public void unzip(String zipFilePath, String destDir) {
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                close(fos, zis);
                ze = zis.getNextEntry();
            }
            close(zis, fis);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Wrong file argument -  {0}", e);
        }
    }

    @Override
    public void setInitialProperties(String zipFilePath) {
        String destDir = System.getProperty("user.dir");
        unzip(zipFilePath, destDir);

        super.setInitialProperties(PARAMETERS_FILE); //To change body of generated methods, choose Tools | Templates.
    }

    private void close(FileOutputStream fos, ZipInputStream zis) throws IOException {
        fos.close();
        zis.closeEntry();
    }

    private void close(ZipInputStream zis, FileInputStream fis) throws IOException {
        zis.closeEntry();
        zis.close();
        fis.close();
    }

}
