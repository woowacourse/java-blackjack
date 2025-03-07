package blackjack.domain.gambler;

import blackjack.domain.Hands;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Gambler {
    protected final Hands hands = new Hands();
    private final Name name;

    public Gambler(final Name name) {
        this.name = name;
    }

    public abstract List<Card> getInitialCards();

    public boolean isNameEquals(Name name) {
        return Objects.equals(name, this.name);
    }

    public void addCard(final Card card) {
        hands.addCard(card);
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public boolean isScoreBelow(final int criteria) {
        return hands.isScoreBelow(criteria);
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public Name getName() {
        return name;
    }
}
