package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.ResultStatus;

public class Dealer extends Participant {
    public static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer createDefaultDealer(final Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    @Override
    public boolean isReceivable() {
        return cards.calculate() <= RECEIVE_SIZE;
    }
}
