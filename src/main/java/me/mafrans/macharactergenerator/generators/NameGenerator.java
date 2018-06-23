package me.mafrans.macharactergenerator.generators;

import me.mafrans.macharactergenerator.objects.classes.Title;
import me.mafrans.macharactergenerator.objects.enums.BaseStat;
import me.mafrans.macharactergenerator.objects.enums.Gender;
import me.mafrans.macharactergenerator.parents.abstracts.MaBase;
import me.mafrans.macharactergenerator.util.MaArrayUtil;
import me.mafrans.macharactergenerator.util.MaFileUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NameGenerator extends MaBase {
    public File file = null;
    public String resource = null;

    public NameGenerator(File file) {
        this.file = file;
    }
    public NameGenerator(String resource) {
        this.resource = resource;
    }

    public String[] getFirstNames() {
        JSONObject data = getData();

        JSONObject firstNameObj = data.getJSONObject("first_name");
        JSONArray femaleNames = firstNameObj.getJSONArray("female");
        JSONArray maleNames = firstNameObj.getJSONArray("male");
        JSONArray otherNames = firstNameObj.getJSONArray("other");

        String[] allNames = (String[]) MaArrayUtil.addAll(new String[][] {
                femaleNames.toList().toArray(new String[0]),
                maleNames.toList().toArray(new String[0]),
                otherNames.toList().toArray(new String[0])
        });

        return allNames;
    }

    public String[] getFirstNames(Gender gender) {
        JSONObject data = getData();

        JSONObject firstNameObj = data.getJSONObject("first_name");
        JSONArray femaleNames = firstNameObj.getJSONArray("female");
        JSONArray maleNames = firstNameObj.getJSONArray("male");
        JSONArray otherNames = firstNameObj.getJSONArray("other");

        switch(gender) {
            case MALE:
                return maleNames.toList().toArray(new String[0]);

            case FEMALE:
                return femaleNames.toList().toArray(new String[0]);

            case OTHER:
                return otherNames.toList().toArray(new String[0]);
        }

        return new String[0];
    }

    public String getRandomFirstName() {
        Random random = new Random();

        return getFirstNames()[random.nextInt(getFirstNames().length)];
    }

    public String getRandomLastName() {
        JSONObject data = getData();
        StringBuilder lastNameBuilder = new StringBuilder();

        Random random = new Random();
        JSONArray lastNames = data.getJSONArray("last_name");

        for(int i = 0; i < lastNames.length(); i++) {
            String[] nameParts = lastNames.getJSONArray(i).toList().toArray(new String[0]);

            while(true) {
                String randomPart = nameParts[random.nextInt(nameParts.length)];
                if (i == 0) {
                    lastNameBuilder.append(randomPart);
                    break;
                }

                if(lastNameBuilder.toString().endsWith(randomPart.substring(0,1))) {
                    continue;
                }
                else {
                    lastNameBuilder.append(randomPart.toLowerCase());
                    break;
                }
            }
        }

        return lastNameBuilder.toString();
    }

    public Title[] getTitles() {
        JSONObject data = getData();
        JSONObject titleObject = data.getJSONObject("title");
        JSONArray prefixArray = titleObject.getJSONArray("prefix");
        JSONArray suffixArray = titleObject.getJSONArray("suffix");

        List<Title> outList = new ArrayList<>();

        for(int i = 0; i < prefixArray.length(); i++) {
            JSONObject currentObject = prefixArray.getJSONObject(i);
            outList.add(new Title(currentObject.getString("name"), ""));
        }

        for(int i = 0; i < suffixArray.length(); i++) {
            JSONObject currentObject = suffixArray.getJSONObject(i);
            outList.add(new Title("", currentObject.getString("name")));
        }

        return outList.toArray(new Title[0]);
    }

    public Title getRandomTitle(Map<BaseStat,Integer> stats) {
        JSONObject data = getData();
        JSONObject titleObject = data.getJSONObject("title");
        JSONArray prefixArray = titleObject.getJSONArray("prefix");
        JSONArray suffixArray = titleObject.getJSONArray("suffix");
        Random random = new Random();


        Title title = new Title("","");
        for(int i = 0; i < prefixArray.length(); i++) {
            if(!title.getPrefix().isEmpty()) break; // Prefix has already been set

            JSONObject currentObject = prefixArray.getJSONObject(i);

            String currentName = currentObject.getString("name");
            int currentBaseChance = currentObject.getInt("chance");
            BaseStat currentStat = BaseStat.valueOf(currentObject.getString("base_stat").toUpperCase());
            int currentIncrease = currentObject.getInt("increase");

            int statValue = 0;
            if(stats.containsKey(currentStat)) {
                statValue = stats.get(currentStat);
            }


            double totalChance = (Math.max(0, ((currentBaseChance + (currentIncrease * statValue)) / 100.00))); // Chance of applying prefix
            double randomDouble = random.nextDouble();
            if(randomDouble < totalChance) { // Apply prefix, yay
                title.setPrefix(currentName);
            }
        }

        for(int i = 0; i < suffixArray.length(); i++) {
            if(!title.getSuffix().isEmpty()) break; // Prefix has already been set

            JSONObject currentObject = suffixArray.getJSONObject(i);

            String currentName = currentObject.getString("name");
            int currentBaseChance = currentObject.getInt("chance");
            BaseStat currentStat = BaseStat.valueOf(currentObject.getString("base_stat").toUpperCase());
            int currentIncrease = currentObject.getInt("increase");

            int statValue = 0;
            if(stats.containsKey(currentStat)) {
                statValue = stats.get(currentStat);
            }


            double totalChance = (Math.max(0, ((currentBaseChance + (currentIncrease * statValue)) / 100.00))); // Chance of applying prefix
            double randomDouble = random.nextDouble();
            if(randomDouble < totalChance) { // Apply suffix, yay
                title.setSuffix(currentName);
            }
        }

        return title;
    }

    public JSONObject getData() {
        try {
            if(file != null) {
                return new JSONObject(MaFileUtil.readFile(file));
            }
            else if(resource != null) {
                return new JSONObject(MaFileUtil.readResource(resource));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}

