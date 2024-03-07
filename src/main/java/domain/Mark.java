package domain;

import java.util.Arrays;
import java.util.List;

public enum Mark {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String name;

    Mark(String name) {
        this.name = name;
    }

    public static List<Mark> getValues() {
        return Arrays.stream(Mark.values())
                .toList();
    }

    public String getName() {
        return this.name;
    }
}
