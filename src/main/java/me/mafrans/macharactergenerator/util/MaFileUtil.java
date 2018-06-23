package me.mafrans.macharactergenerator.util;

import org.apache.commons.io.IOUtils;

import java.io.*;


public class MaFileUtil {
    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public static String readResource(String path) throws IOException {
        if(FileCache.getResourceCache().containsKey(path)) {
            return FileCache.getCachedContent(path);
        }

        InputStream resourceStream = classLoader.getResourceAsStream(path);
        String theString = IOUtils.toString(resourceStream, "UTF-8");

        FileCache.cache(path, theString);

        return theString;
    }

    public static String readFile(File file) throws IOException {
        if(FileCache.getFileCache().containsKey(file)) {
            return FileCache.getFileCache().get(file);
        }

        String theString = IOUtils.toString(new FileInputStream(file));

        FileCache.cache(file, theString);

        return theString;
    }
}
