package blackjack.domain;

import java.util.Objects;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        validateNameCharacter(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d글자 이하만 가능합니다.", MAX_NAME_LENGTH));
        }
    }

    private void validateNameCharacter(String name) {
        if (hasInvalidCharacter(name)) {
            throw new IllegalArgumentException(String.format("이름은 한글 또는 영어만 가능합니다. 입력값: %s", name));
        }
    }

    private boolean hasInvalidCharacter(String name) {
        return name.chars().anyMatch(this::isInvalidCharacter);
    }

    private boolean isInvalidCharacter(int character) {
        return !(isKorean(character) || isLowerAlphabet(character) || isUpperAlphabet(character));
    }

    private boolean isKorean(int character) {
        return '가' <= character && character <= '힣';
    }

    private boolean isLowerAlphabet(int character) {
        return 'a' <= character && character <= 'z';
    }

    private boolean isUpperAlphabet(int character) {
        return 'A' <= character && character <= 'Z';
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name name1)) {
            return false;
        }
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
