package domain.card;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Denomination {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String name;
    private final int value;

    Denomination(String name, int value) {
        this.name = name;
        this.value = value;
    }

//    public List<String> getDenominationNames() {
//        return Arrays.stream(values())
//                .map(denomination -> denomination.name)
//                .collect(Collectors.toUnmodifiableList());
//    }

    public String getName() {
        return name;
    }

    public int getValueByName(String name) {
        return valueOf(name).value;
    }
}
