package domain.participant;

import java.util.Objects;

public class ParticipantName {
    private static final String BLANK_NAME = "닉네임은 공백일 수 없습니다";
    private final String name;

    public ParticipantName(String name) {
        validateName(name);
        this.name = name;
    }


    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME);
        }
    }

    public boolean isMatch(ParticipantName name) {
        return this.equals(name);
    }

    public String name() {
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
        ParticipantName that = (ParticipantName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
