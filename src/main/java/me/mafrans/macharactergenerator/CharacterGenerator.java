package me.mafrans.macharactergenerator;

import me.mafrans.macharactergenerator.generators.NameGenerator;
import me.mafrans.macharactergenerator.parents.abstracts.MaBase;
import me.mafrans.macharactergenerator.util.FileCache;

public class CharacterGenerator {
    public FileCache fileCache;
    public NameGenerator nameGenerator;

    public CharacterGenerator() {
        MaBase.main = this;
        fileCache = new FileCache();
        fileCache.cache("First Names/EldariFirstNames.json");

        nameGenerator = new NameGenerator();
    }

    public static void main(String[] args) {
        CharacterGenerator characterGenerator = new CharacterGenerator();
    }
}
