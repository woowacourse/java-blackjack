package blackjack.domain;

import java.util.List;

// TODO 기능을 위한 has a 관계로 바라보기
public class Dealer extends Participant {
    private static final HandsScore DEALER_DRAW_THRESHOLD = new HandsScore(16);

    public Dealer() {
        super(Name.dealerName());
    }

    public PlayerState draw(Deck deck) {
        if (shouldDraw()) {
            addCard(deck.draw());
        }
        return canNextDraw();
    }

    public Card getFirstCard() {
        List<Card> cards = hands.getHands();
        if (cards.isEmpty()) {
            throw new IllegalStateException("아직 지니고 있는 카드가 없습니다");
        }

        return hands.getHands()
                .get(0);
    }

    private PlayerState canNextDraw() {
        if (shouldDraw()) {
            return PlayerState.RUNNING;
        }
        return PlayerState.FINISHED;
    }

    private boolean shouldDraw() {
        HandsScore handsScore = getHandsScore();
        return handsScore.isSame(DEALER_DRAW_THRESHOLD)
                || handsScore.isLowerThan(DEALER_DRAW_THRESHOLD);
    }
}
