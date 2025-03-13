package blackjack.domain.gambler;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.game.Round.BLACKJACK_CARD_COUNT;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;
import java.util.Objects;

public class Player implements Gambler {
    private final Name name;
    private final Hands hands = new Hands();

    public Player(final Name name) {
        this.name = name;
    }

    @Override
    public void addCard(final Card card) {
        hands.addCard(card);
    }

    @Override
    public int calculateScore() {
        return hands.calculateScore();
    }

    @Override
    public boolean isScoreBelow(final int criteria) {
        return hands.isScoreBelow(criteria);
    }

    @Override
    public int calculateScoreDifference(final Gambler other) {
        return calculateScore() - other.calculateScore();
    }

    @Override
    public boolean isBlackjack() {
        return hands.hasSize(BLACKJACK_CARD_COUNT) && calculateScore() == BLACKJACK;
    }

    @Override
    public boolean isNameEquals(final Name name) {
        return Objects.equals(name, this.name);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return hands.getCards();
    }

    @Override
    public List<Card> getInitialCards() {
        return getCards();
    }
}
