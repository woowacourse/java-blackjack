package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    private final String name;
    private final HoldingCard holdingCard;

    public Player(String name, List<Card> cards) {
        validateEmptyName(name);
        this.name = name;
        this.holdingCard = new HoldingCard(cards);
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public boolean isFinished() {
        return holdingCard.isBust();
    }
}
