package blackjack.domain.participant;

import java.util.Objects;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String MAX_NAME_LENGTH_ERR_MSG = String.format("플레이어 이름 길이는 최대 %d자 입니다.", MAX_NAME_LENGTH);
    private static final String NULL_NAME_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String EMPTY_NAME_ERR_MSG = "플레이어 이름에 빈 값이 올 수 없습니다.";

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        Objects.requireNonNull(name, NULL_NAME_ERR_MSG);

        if (name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERR_MSG);
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(MAX_NAME_LENGTH_ERR_MSG);
        }
    }

    public String getName() {
        return name;
    }
}
