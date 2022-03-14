package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    private boolean finish = false;

    public Player(String name, List<Card> cards) {
        super(name, new HoldingCards(cards));
        validateEmptyName(name);
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public void closeTurn() {
        finish = true;
    }

    public boolean isFinished() {
        return super.getHoldingCard().isBlackJackOrBust() || finish;
    }

}
