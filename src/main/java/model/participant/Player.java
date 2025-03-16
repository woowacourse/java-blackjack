package model.participant;

import java.util.List;
import model.card.Card;
import model.card.CardHand;
import model.state.PlayerHit;

public final class Player extends Participant {
    private final Name name;
    private final Bet bet;

    public Player(final Name name, final Bet bet, final CardHand initialHand) {
        super(PlayerHit.initialState(initialHand));
        this.name = name;
        this.bet = bet;
    }

    @Override
    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return cardHand.getCards();
    }

    @Override
    public Name getName() {
        return name;
    }
}
