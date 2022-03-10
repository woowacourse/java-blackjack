package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

public class Dealer {

    private final Name name = new Name("딜러");
    private final Hand cardHand = new Hand();
    private Deck deck;

    public Dealer() {
        this.deck = Deck.createFixedCards();
    }

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public Card drawCards() {
        return deck.draw();
    }

    public void selfDraw() {
        Card card = deck.draw();
        cardHand.add(card);
    }

    public boolean shouldReceive() {
        return cardHand.getScore() < 17;
    }
}
