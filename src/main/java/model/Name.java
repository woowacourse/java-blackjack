package model;

import java.util.List;
import java.util.stream.Collectors;

public class Name {

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name from(final String name) {
        return new Name(name);
    }

    public static Name withScore(final int score, final String name) {
        return new Name(score + name);
    }

    public static List<Name> convertStringListToNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }
}
