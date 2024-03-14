package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;

public class Player2 {

    private static final int REVEAL_COUNT = 2;

    private final Name name;
    private final Hand hand;

    public Player2(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void deal(CardDeck cardDeck) {
        hand.appendInitial(cardDeck);
    }

    public Hand revealHand() {
        return hand.revealHand(REVEAL_COUNT);
    }

    public Name getName() {
        return name;
    }
}
