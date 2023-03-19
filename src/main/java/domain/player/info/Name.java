package domain.player.info;

import java.util.Objects;

public final class Name {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validateBlank(name);
        validateLength(name);
        validateImpersonate(name);

        return new Name(name);
    }

    private static void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름을 입력해 주세요");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("10자 이하의 이름만 입력해 주세요");
        }
    }


    private static void validateImpersonate(final String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("참가자의 이름은 딜러가 될 수 없습니다.");
        }
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
