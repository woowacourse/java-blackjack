package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

import java.util.List;

public abstract class Gamer {

    protected final Hands hands;
    private String name;

    public Gamer(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

    abstract public List<Card> showInitialHands();

    public String getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
