package domain.participant;

import java.util.Objects;

public final class ParticipantName {

    private static final int MAX_LENGTH = 20;

    private final String name;

    private ParticipantName(final String name) {
        validateBlankName(name);
        validateNameLength(name);

        this.name = name;
    }

    public static ParticipantName create(final String name) {
        validateNullName(name);

        return new ParticipantName(name);
    }

    private static void validateNullName(final String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("이름은 Null일 수 없습니다.");
        }
    }

    private void validateBlankName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 1글자에서 20글자 사이여야 합니다.");
        }
    }

    String getName() {
        return name;
    }
}
