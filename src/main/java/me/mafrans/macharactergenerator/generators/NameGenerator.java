package me.mafrans.macharactergenerator.generators;

import me.mafrans.macharactergenerator.parents.abstracts.MaBase;
import me.mafrans.macharactergenerator.parents.interfaces.INameGenerator;
import me.mafrans.macharactergenerator.util.MaFileUtil;
import org.json.JSONArray;

import java.util.Arrays;

public class NameGenerator extends MaBase {
    public NameGenerator() {
        logger.info(Arrays.toString(new EldariNameGenerator().getFirstNames()));
    }
}

class EldariNameGenerator extends MaBase implements INameGenerator {

    public String[] getFirstNames() {
        String jsonArrayString = main.fileCache.getCachedContent("First Names/EldariFirstNames.json");
        JSONArray jsonArray = new JSONArray(jsonArrayString);

        return jsonArray.toList().toArray(new String[0]);
    }

    public String[] getLastNames() {
        return new String[0];
    }

    public String[] getTitles() {
        return new String[0];
    }
}
