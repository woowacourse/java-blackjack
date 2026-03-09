package domain;

import java.util.List;

public class Player extends Participant {
    private final String name;

    public Player(String name, List<Card> hand) {
        super(hand);
        validateEmptyNames(name);
        this.name = name;
    }

    private void validateEmptyNames(String playerName) {
        if (playerName.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 빈 값이 아니여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
