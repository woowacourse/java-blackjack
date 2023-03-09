package domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PlayerName {

    private static final int NAME_MAX_LENGTH = 5;
    private static final String DEALER_NAME = "딜러";

    private final String name;

    public PlayerName(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateBlank(name);
        validateLength(name);
        validateProperName(name);
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백으로만 이루어질 수 없습니다.");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("플레이어의 이름은 %d보다 길 수 없습니다.", NAME_MAX_LENGTH));
        }
    }

    private void validateProperName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("딜러라는 이름은 사용할 수 없습니다.");
        }
    }

    public static void validateDuplication(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerName that = (PlayerName) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
