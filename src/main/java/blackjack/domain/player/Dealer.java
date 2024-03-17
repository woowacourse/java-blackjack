package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.info.Name;

public class Dealer extends Participant {
    private final Name name;
    public static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer(final Name name, final Cards cards) {
        super(cards);
        this.name = name;
    }

    public Dealer(final Name name) {
        this.name = name;
    }

    public static Dealer createDefaultNameDealer() {
        return new Dealer(new Name(DEFAULT_DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return this.state.calculate() <= RECEIVE_SIZE;
    }

    public Card getFirstCard() {
        return this.state.getCards()
                         .get(0);
    }

    @Override
    public String getNameAsString() {
        return this.name.asString();
    }

    public Name getName() {
        return this.name;
    }
}
