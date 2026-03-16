package domain;

import java.util.List;
import java.util.Objects;

public class Name {
    private static final int NAME_LIMIT_LENGTH = 20;
    private static final List<String> INVALID_NAMES = List.of("딜러", "dealer", "참가자");

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        return new Name(validateName(name));
    }

    private static String validateName(String name) {
        validateNullOrEmpty(name);
        String trimmedName = name.trim();
        validateLength(trimmedName);
        validateInvalidName(trimmedName);
        return trimmedName;
    }

    private static void validateLength(String name) {
        if (name.length() > NAME_LIMIT_LENGTH) {
            throw new IllegalArgumentException("이름은 20자 이하로 입력해주세요.");
        }
    }

    private static void validateNullOrEmpty(String name) {
        if ( name == null|| name.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateInvalidName(String name) {
        String checkName = name.replaceAll("\\s+", "");

        if (INVALID_NAMES.contains(checkName.toLowerCase())) {
            throw new IllegalArgumentException("사용할 수 없는 이름입니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Name name1 = (Name) obj;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
