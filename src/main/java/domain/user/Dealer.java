package domain.user;

import domain.card.Card;
import java.util.List;

public class Dealer extends Player {

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public int calculatePoint() {
        int point = 0;
        for (Card card : hand) {
            point += card.getDenomination().getPoint();
        }
        if (hasAce()) {
            return calculateAce(point);
        }
        return point;
    }

    @Override
    protected int calculateAce(int point) {
        if (point > 6) {
            return point + 10;
        }
        return point;
    }

    @Override
    public List<Card> faceUpInitialHand() {
        if (hand.size() < 2) {
            throw new IllegalStateException("딜러는 카드 두장을 받고 시작해야 합니다.");
        }
        return List.of(hand.get(0));
    }
}
