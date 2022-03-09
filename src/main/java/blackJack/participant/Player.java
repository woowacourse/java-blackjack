package blackJack.participant;

public class Player {
    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }
}
