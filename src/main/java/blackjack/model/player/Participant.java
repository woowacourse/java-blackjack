package blackjack.model.player;

import java.util.Objects;

public class Participant extends Player {
    public static final int MINIMUM_NAME_LENGTH = 2;
    public static final int MAXMIUM_NAME_LENGTH = 5;

    private final String name;

    public Participant(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참여자 이름을 입력해주세요.");
        }
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXMIUM_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("참여자 이름은 %d~%d글자 입니다.", MINIMUM_NAME_LENGTH, MAXMIUM_NAME_LENGTH));
        }
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
}
