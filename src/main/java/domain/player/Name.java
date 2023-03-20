package domain.player;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Name {
    private static final Pattern NAME_FORMAT = Pattern.compile("[가-힣|a-zA-Z0-9]+");
    public static final String DEALER_NAME = "딜러";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validateName(name);
        return new Name(name);
    }

    public static List<Name> of(List<String> names) {
        return names.stream()
                .map(Name::of)
                .collect(Collectors.toUnmodifiableList());
    }

    public static Name dealerName() {
        return new Name(DEALER_NAME);
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }

        if (!NAME_FORMAT.matcher(name).matches()) {
            throw new IllegalArgumentException("한글, 영어 대/소문자와 숫자만 이름이 될 수 있습니다.");
        }

        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("사용할 수 없는 이름입니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
