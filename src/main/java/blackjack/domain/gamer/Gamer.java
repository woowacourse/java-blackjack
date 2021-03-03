package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public abstract class Gamer {

    private String name;
    private final Hands hands;

    public Gamer(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

    public String getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
