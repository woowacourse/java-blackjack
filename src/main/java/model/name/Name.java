package model.name;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.Keyword.ACE;
import static util.Keyword.BLANK;
import static util.Keyword.DEALER;
import static util.Keyword.NAME_SPLITTER;

public class Name {

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name from(final String name) {
        return new Name(name);
    }

    public static List<Name> convertStringListToNamesWithScore(final int score, final List<String> names) {
        return names.stream()
                .map(name -> Name.withScore(score, name))
                .collect(Collectors.toList());
    }

    private static Name withScore(final int score, final String name) {
        return new Name(score + name);
    }

    public static List<Name> convertStringListToNamesWithSpecial(final String special, final List<String> names) {
        return names.stream()
                .map(name -> Name.from(special + name))
                .collect(Collectors.toList());
    }

    public static List<String> createSplitNameValues(String input) {
        return Arrays.stream(input.split(NAME_SPLITTER.getValue()))
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }

    public static String chainingNames(List<Name> names) {
        StringBuilder stringBuilder = new StringBuilder();
        names.forEach(name -> stringBuilder
                .append(name.getName())
                .append(NAME_SPLITTER.getValue())
                .append(BLANK.getValue()));

        String chainingName = stringBuilder.toString();
        return eraseLastMark(chainingName);
    }

    private static String eraseLastMark(final String chainingName) {
        return chainingName.substring(0, chainingName.length() - (NAME_SPLITTER.length() + BLANK.length()));
    }

    public static boolean isAce(Name value) {
        String valueName = value.getName();
        return valueName.startsWith(ACE.getValue());
    }

    public boolean isNotDealer() {
        return !name.equals(DEALER.getValue());
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

    public String getName() {
        return name;
    }
}
