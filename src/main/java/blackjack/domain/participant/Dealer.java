package blackjack.domain.participant;

import blackjack.domain.deck.Deck;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;

    public Dealer() {
        super(DEALER_NAME);
    }

    public void draw(Deck deck) {
        if (shouldDraw()) {
            addCard(deck.draw());
        }
    }

    public String getFirstCardName() {
        if (hands.size() < INITIAL_DRAW_CARD_NUMBER) {
            throw new UnsupportedOperationException("초기 카드 분배 후에 사용할 수 있습니다.");
        }
        return hands.getHands()
                .get(0)
                .getCardName();
    }

    private boolean shouldDraw() {
        return hands.getHandsScore().getScore() <= DEALER_DRAW_THRESHOLD;
    }

    @Override
    public boolean canDraw() {
        return shouldDraw();
    }
}
