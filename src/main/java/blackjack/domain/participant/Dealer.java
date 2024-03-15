package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

import static blackjack.domain.game.BlackjackGame.STARTING_CARDS_AMOUNT;

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
            throw new IllegalStateException("딜러 카드가 비어 있습니다.");
        }
        if (getCards().size() <= STARTING_CARDS_AMOUNT) {
            return List.of(getCards().get(0));
        }
        return getCards();
    }
}
