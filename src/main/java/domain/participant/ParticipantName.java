package domain.participant;

import java.util.Objects;

public final class ParticipantName {

    private static final int MAX_LENGTH = 20;

    private final String name;

    private ParticipantName(final String name) {
        this.name = name;
    }

    public static ParticipantName create(final String name) {
        validateNullName(name);
        validateBlankName(name);
        validateNameLength(name);
        return new ParticipantName(name);
    }

    private static void validateNullName(final String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("이름은 Null일 수 없습니다.");
        }
    }

    private static void validateBlankName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    private static void validateNameLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 1글자에서 20글자 사이여야 합니다.");
        }
    }

    boolean isSame(final String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof ParticipantName)) {
            return false;
        }
        ParticipantName targetName = (ParticipantName) target;
        return Objects.equals(name, targetName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    String getName() {
        return name;
    }
}
