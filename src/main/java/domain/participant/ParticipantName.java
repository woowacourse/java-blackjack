package domain.participant;


import java.util.Objects;

public record ParticipantName(String name) {

    private static final int PLAYER_NAME_LENGTH_LIMIT = 5;

    public static ParticipantName from(String name) {
        validateBlank(name);
        validateNameLength(name);
        return new ParticipantName(name);
    }

    private static void validateNameLength(String name) {
        if (name.length() > PLAYER_NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParticipantName that = (ParticipantName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
