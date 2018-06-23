package me.mafrans.macharactergenerator.util;

import java.util.ArrayList;
import java.util.List;

public class MaArrayUtil {
    public static Object[] addAll(Object[][] arrays) {
        List<Object> outList = new ArrayList<>();

        for(Object[] array : arrays) {
            for(Object object : array) {
                outList.add(object);
            }
        }

        return outList.toArray(new String[0]);
    }
}
