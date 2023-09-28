package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Name {

    private static final String DEALER = "딜러";
    private static final String NAME_SPLITTER = ",";

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

    public static List<Name> createSplitNames(String input) {
        List<String> splitNames = Arrays.asList(input.split(NAME_SPLITTER));

        return splitNames.stream()
                .map(String::trim)
                .map(Name::from)
                .collect(Collectors.toList());
    }

    public static String chainingNames(List<Name> names) {
        StringBuilder stringBuilder = new StringBuilder();
        names.forEach(name -> stringBuilder
                .append(name.getName())
                .append(Name.NAME_SPLITTER));

        String chainingName = stringBuilder.toString();
        return chainingName.substring(0, chainingName.length() - 1);
    }

    public boolean isNotDealer() {
        return !name.equals(DEALER);
    }

    public static String getDealer() {
        return DEALER;
    }

    public String getName() {
        return name;
    }
}
