package blackjack.model.player;

import java.util.Objects;

public class Participant extends Player {
    private final String name;

    public Participant(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참여자 이름을 입력해주세요.");
        }
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("참여자 이름은 2~5글자 입니다.");
        }
    }
}
