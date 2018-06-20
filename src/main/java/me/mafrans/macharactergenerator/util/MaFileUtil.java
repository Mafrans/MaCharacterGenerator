package me.mafrans.macharactergenerator.util;

import org.apache.commons.io.IOUtils;

import java.io.*;


public class MaFileUtil {
    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public static String readResource(String path) throws IOException {
        InputStream resourceStream = classLoader.getResourceAsStream(path);
        String theString = IOUtils.toString(resourceStream, "UTF-8");

        return theString;
    }

    public static String readFile(File file) throws IOException {
        String theString = IOUtils.toString(new FileInputStream(file));

        return theString;
    }
}
