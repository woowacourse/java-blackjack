package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;

public abstract class BlackjackGamer {

    private final Hand hand;

    public BlackjackGamer() {
        this.hand = new Hand(new ArrayList<>());
    }

    public BlackjackGamer(Hand hand) {
        this.hand = hand;
    }

    public abstract boolean canReceiveCard();

    public void initCard(Deck deck) {
        addCard(deck.draw());
        addCard(deck.draw());
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Hand getHand() {
        return hand;
    }

    public HandValue getHandValue() {
        return hand.getValue();
    }

    public int getScore() {
        return getHandValue().getScore();
    }
}
