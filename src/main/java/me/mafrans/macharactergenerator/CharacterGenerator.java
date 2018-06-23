package me.mafrans.macharactergenerator;

import me.mafrans.macharactergenerator.generators.NameGenerator;
import me.mafrans.macharactergenerator.objects.classes.Title;
import me.mafrans.macharactergenerator.objects.enums.BaseStat;
import me.mafrans.macharactergenerator.parents.abstracts.MaBase;
import me.mafrans.macharactergenerator.util.FileCache;

import java.util.HashMap;
import java.util.Map;

public class CharacterGenerator {
    public static void main(String[] args) {
        NameGenerator eldariNameGen = new NameGenerator("Eldari.json");
        for(int i = 0; i < 50; i++) {
            Map<BaseStat, Integer> baseStatMap = new HashMap<>();
            baseStatMap.put(BaseStat.INTELLIGENCE, 4);

            String name = eldariNameGen.getRandomFirstName();
            String lastName = eldariNameGen.getRandomLastName();
            Title title = eldariNameGen.getRandomTitle(baseStatMap);
            String prefix = title.getPrefix().isEmpty() ? "" : title.getPrefix() + " ";
            String suffix = title.getSuffix().isEmpty() ? "" : " " + title.getSuffix();

            System.out.println(prefix + name + " " + lastName + suffix);
        }
    }
}
