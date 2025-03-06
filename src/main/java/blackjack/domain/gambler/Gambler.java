package blackjack.domain.gambler;

import blackjack.domain.Hands;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Gambler {
    private final Name name;

    public Gambler(final Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public boolean isNameEquals(Name name) {
        return Objects.equals(name, this.name);
    }

    protected final Hands hands = new Hands();

    public void addCard(final Card card) {
        hands.addNewCard(card);
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public boolean isScoreBelow(final int criteria) {
        return hands.isScoreBelow(criteria);
    }
}
