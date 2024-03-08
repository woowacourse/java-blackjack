package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {

    public static final int THRESHOLD_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return score() <= THRESHOLD_SCORE;
    }

    public Card findShowingCard() {
        if (getCards().isEmpty()) {
            throw new IllegalStateException("[ERROR] 딜러 카드가 비어 있습니다.");
        }
        return getCards().get(0);
    }
}
