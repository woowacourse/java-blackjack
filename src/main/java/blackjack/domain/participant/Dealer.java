package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    public static final int THRESHOLD_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return calculateScore() <= THRESHOLD_SCORE;
    }

    @Override
    public List<Card> findShowingCards() {
        if (getCards().isEmpty()) {
            throw new IllegalStateException("[ERROR] 딜러 카드가 비어 있습니다.");
        }
        if (getCards().size() == 2) {
            return List.of(getCards().get(0));
        }
        return getCards();
    }
}
