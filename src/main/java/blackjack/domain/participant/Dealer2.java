package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer2 {

    private static final int REVEAL_COUNT = 1;

    private final Hand hand;

    public Dealer2(Hand hand) {
        this.hand = hand;
    }

    public void deal(CardDeck cardDeck) {
        hand.appendInitial(cardDeck);
    }

    public List<Card> revealHand() {
        return hand.revealHand(REVEAL_COUNT);
    }
}
