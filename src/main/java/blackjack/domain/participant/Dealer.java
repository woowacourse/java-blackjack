package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    private static final int SPECIFIC_SCORE_OF_DEALER = 16;

    public Dealer() {
        super(Cards.generateEmptyCards());
    }

    public Card getOneCardToShow() {
        return cards.getCards().get(0);
    }

    public boolean canDraw() {
        return cards.calculateScoreForBlackjack() <= SPECIFIC_SCORE_OF_DEALER;
    }
}
