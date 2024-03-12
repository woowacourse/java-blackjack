package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;

public abstract class BlackjackGamer {

    private final Hand hand;

    public BlackjackGamer() {
        this.hand = new Hand(new ArrayList<>());
    }

    public abstract boolean canReceiveCard();

    public void initCard(Deck deck) {
        addCard(deck.draw());
        addCard(deck.draw());
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }
}
