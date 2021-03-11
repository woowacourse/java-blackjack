package blackjack.domain.gamer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public abstract class Gamer {

    protected final Hands hands;
    private final String name;

    protected Gamer(final String name, final Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public abstract List<Card> showOpenHands();

    public List<Card> showHands() {
        return hands.toList();
    }

    public String getName() {
        return name;
    }

    public Score calculateScore() {
        return hands.calculate();
    }

    public boolean isBusted() {
        return calculateScore().isBusted();
    }

    public boolean isMaxScore() {
        return hands.isMaxScore();
    }

    public boolean isUnderMaxScore() {
        return hands.isUnderMaxScore();
    }

    public boolean hasBlackjack() {
        return hands.isBlackjack();
    }

    public void receiveCard(final Card card) {
        hands.addCard(card);
    }

    public int getScore() {
        return calculateScore().getValue();
    }
}
