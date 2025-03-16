package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Deck;

public final class Player extends Participant {
    private final Name name;
    private final Bet bet;

    public Player(final Name name, final Bet bet, final Deck deck) {
        super(deck);
        this.name = name;
        this.bet = bet;
    }

    @Override
    public List<Card> openInitialCard() {
        return cardHand.getCards();
    }

    @Override
    public Name getName() {
        return name;
    }
}
