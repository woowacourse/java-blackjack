package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

import java.util.List;

public abstract class Gamer {

    protected final Hands hands = new Hands();
    private String name;

    protected Gamer(String name) {
        this.name = name;
    }

    public abstract List<Card> showOpenHands();

    public List<Card> showHands() {
        return hands.toList();
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return hands.calculate();
    }

    public boolean hasBlackjack() {
        return hands.isBlackjack();
    }

    public void receiveCard(Card card) {
        hands.addCard(card);
    }

    public void initHands(List<Card> makeInitialHands) {
        hands.makeWith(makeInitialHands);
    }
}
