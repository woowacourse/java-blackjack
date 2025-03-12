package blackjack.domain.gambler;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.game.Round.BLACKJACK_CARD_COUNT;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Gambler {
    protected final Hands hands = new Hands();
    private final Name name;

    protected Gambler(final Name name) {
        this.name = name;
    }

    public boolean isNameEquals(final Name name) {
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

    public int calculateScoreDifference(final Gambler other) {
        return calculateScore() - other.calculateScore();
    }

    public boolean isBlackjack() {
        return hands.hasSize(BLACKJACK_CARD_COUNT) && calculateScore() == BLACKJACK;
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public Name getName() {
        return name;
    }

    public abstract boolean isPlayer();

    public abstract List<Card> getInitialCards();
}
