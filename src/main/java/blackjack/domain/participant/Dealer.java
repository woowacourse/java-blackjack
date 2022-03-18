package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    public static final String NAME = "딜러";
    public static final int DRAW_STANDARD = 16;
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    public Card getFirstCard() {
        return this.cards.getValue()
                .get(FIRST_CARD_INDEX);
    }

    @Override
    public boolean isFinished() {
        return cards.sum() > DRAW_STANDARD;
    }
}
