package domain.user;

import domain.card.Card;
import java.util.List;

public class Dealer extends Player {

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> getInitialHand() {
        if (hand == null || hand.getSize() < INITIAL_HAND_COUNT) {
            throw new IllegalStateException("딜러는 카드 두장을 받고 시작해야 합니다.");
        }
        List<Card> card = hand.getAllCards();
        return List.of(card.get(0));
    }
}
