package domain.participant;

import java.util.Objects;

public class ParticipantName {

    private final String name;

    public ParticipantName(String name) {
        validateBlank(name);
        this.name = name;
    }

    private void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 빈값을 입력 할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantName participantName1 = (ParticipantName) o;
        return Objects.equals(name, participantName1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
