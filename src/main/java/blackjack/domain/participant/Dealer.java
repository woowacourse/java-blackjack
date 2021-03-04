package blackjack.domain.participant;

import blackjack.domain.Deck;

public class Dealer extends Participant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Deck deck) {
        super(DEALER_NAME, deck);
    }

    @Override
    public void drawCard(Deck deck) {
        if (!isContinue()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
        }
        getHand().addCard(deck.draw());
        if (isOverLimit()) {
            cannotContinue();
        }
    }

    private boolean isOverLimit() {
        return getScore() > DEALER_LIMIT;
    }
}
