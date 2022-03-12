package blackjack.domain.participant;

public class Player extends Participant {

    public static final String NAME_EMPTY_ERROR = "[ERROR] 플레이어 이름에 빈 값이 올 수 없습니다.";

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public String getName() {
        return name;
    }
}
