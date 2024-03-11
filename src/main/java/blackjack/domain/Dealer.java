package blackjack.domain;

import java.util.List;

// TODO 기능을 위한 has a 관계로 바라보기
public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public PlayerState draw(Deck deck) {
        if (shouldDraw()) {
            addCard(deck.draw());
        }
        return canNextDraw();
    }

    public String getFirstCardName() {
        List<Card> cards = hands.getHands();
        if (cards.isEmpty()) {
            throw new IllegalStateException("아직 지니고 있는 카드가 없습니다");
        }

        return hands.getHands()
                .get(0)
                .getCardName();
    }

    private PlayerState canNextDraw() {
        if (shouldDraw()) {
            return PlayerState.RUNNING;
        }
        return PlayerState.FINISHED;
    }

    private boolean shouldDraw() {
        return hands.getHandsScore() <= DEALER_DRAW_THRESHOLD;
    }
}
