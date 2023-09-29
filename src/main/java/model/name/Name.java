package model.name;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Name {

    private static final String DEALER = "딜러";
    private static final String NAME_SPLITTER = ",";
    private static final String BLANK = " ";

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    private static Name withScore(final int score, final String name) {
        return new Name(score + name);
    }

    public static Name from(final String name) {
        return new Name(name);
    }

    public static Name createDealer() {
        return new Name(DEALER);
    }

    public static List<Name> convertStringListToNamesWithScore(final int score, final List<String> names) {
        return names.stream()
                .map(name -> Name.withScore(score, name))
                .collect(Collectors.toList());
    }

    public static List<Name> convertStringListToNamesWithSpecial(final String special, final List<String> names) {
        return names.stream()
                .map(name -> Name.from(special + name))
                .collect(Collectors.toList());
    }

    public static List<String> createSplitNameValues(String input) {
        return Arrays.stream(input.split(NAME_SPLITTER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String chainingNames(List<Name> names) {
        StringBuilder stringBuilder = new StringBuilder();
        names.forEach(name -> stringBuilder
                .append(name.getName())
                .append(Name.NAME_SPLITTER)
                .append(Name.BLANK));

        String chainingName = stringBuilder.toString();
        return chainingName.substring(0, chainingName.length() - (NAME_SPLITTER.length() + Name.BLANK.length()));
    }

    public boolean isNotDealer() {
        return !name.equals(DEALER);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name other = (Name) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static String getDealer() {
        return DEALER;
    }

    public String getName() {
        return name;
    }
}
