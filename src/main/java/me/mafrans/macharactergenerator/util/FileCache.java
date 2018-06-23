package me.mafrans.macharactergenerator.util;

import me.mafrans.macharactergenerator.parents.abstracts.MaBase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FileCache extends MaBase {
    private static Map<File, String> fileCache = new HashMap<>();
    private static Map<String, String> resourceCache = new HashMap<>();

    public static void cache(File file) {
        String content = null;
        try {
             content = MaFileUtil.readFile(file);
        }
        catch (IOException e) {
            logger.warning("Could not cache file " + file.getName() + ", is it not a text document?");
            e.printStackTrace();
            return;
        }

        fileCache.put(file, content);
    }

    public static void cache(String resourcePath) {
        String content = null;
        try {
            content = MaFileUtil.readResource(resourcePath);
        }
        catch (IOException e) {
            logger.warning("Could not cache resource " + resourcePath + ", contact the developer of MaCharacterGenerator!");
            e.printStackTrace();
            return;
        }

        resourceCache.put(resourcePath, content);
    }

    public static void cache(String resourcePath, String content) {
        resourceCache.put(resourcePath, content);
    }

    public static void cache(File file, String content) {
        fileCache.put(file, content);
    }

    public static String getCachedContent(File file) {
        return fileCache.get(file);
    }

    public static String getCachedContent(String resourcePath) {
        return resourceCache.get(resourcePath);
    }

    public static Map<File, String> getFileCache() {
        return fileCache;
    }

    public static Map<String, String> getResourceCache() {
        return resourceCache;
    }
}
