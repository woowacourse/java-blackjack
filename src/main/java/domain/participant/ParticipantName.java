package domain.participant;

import java.util.Objects;

public class ParticipantName {
    private static final int MAX_LENGTH = 5;
    private static final String NAME_NULL = "플레이어 이름은 null을 허용하지 않습니다.";
    private static final String INVALID_NAME_LENGTH = "이름 길이는 1자 이상 5자 이하여야 합니다.";
    private static final ParticipantName DEALER_NAME = new ParticipantName("딜러");

    private final String name;

    public ParticipantName(String name) {
        validate(name);
        this.name = name;
    }

    public static ParticipantName getDealerName() {
        return DEALER_NAME;
    }

    private void validate(String name) {
        validateIsNotNull(name);
        validateLength(name);
    }

    private void validateIsNotNull(String name) {
        if (name == null) {
            throw new NullPointerException(NAME_NULL);
        }
    }

    private void validateLength(String name) {
        if (name.isBlank() || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParticipantName that = (ParticipantName) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getName() {
        return name;
    }
}
