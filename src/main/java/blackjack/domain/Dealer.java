package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {
    private static final HandsScore DEALER_DRAW_THRESHOLD = new HandsScore(16);

    public Dealer() {
        super(Name.dealerName());
    }

    @Override
    public boolean canAddCard() {
        return shouldDraw();
    }

    public Card getFirstCard() {
        List<Card> cards = hands.getHands();
        if (cards.isEmpty()) {
            throw new IllegalStateException("아직 지니고 있는 카드가 없습니다");
        }

        return hands.getHands()
                .get(0);
    }

    private boolean shouldDraw() {
        HandsScore handsScore = getHandsScore();
        return handsScore.isSame(DEALER_DRAW_THRESHOLD)
                || handsScore.isLowerThan(DEALER_DRAW_THRESHOLD);
    }
}
