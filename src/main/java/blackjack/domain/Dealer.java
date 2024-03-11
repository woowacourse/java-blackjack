package blackjack.domain;

import java.util.List;

// TODO 기능을 위한 has a 관계로 바라보기
public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    // TODO이 메서드만 보고 어떤 의미를 나타내는지 파악이 힘듦
    public boolean draw(Deck deck) {
        if (shouldDraw()) {
            return addCard(deck.draw());
        }
        return false;
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

    private boolean shouldDraw() {
        return hands.getHandsScore() <= DEALER_DRAW_THRESHOLD;
    }
}
