package domain.user;

import domain.Card;
import java.util.List;

public class Dealer extends Player {

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> faceUpInitialHand() {
        if (hand.size() < 1) {
            throw new IllegalStateException("딜러는 카드 두장을 받고 시작해야 합니다.");
        }
        return List.of(hand.get(0));
    }
}
