package me.mafrans.macharactergenerator.objects.enums;

public enum BaseStat {
    INTELLIGENCE("Intelligence"),
    WILL("Will"),
    ;

    private String name;
    BaseStat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
