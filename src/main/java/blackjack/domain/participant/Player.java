package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldingCard;
import java.util.List;

public class Player extends Participant {

    public Player(String name, List<Card> cards) {
        super(name, new HoldingCard(cards));
        validateEmptyName(name);
    }


    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public boolean isFinished() {
        return super.getHoldingCard().isBust();
    }
}
