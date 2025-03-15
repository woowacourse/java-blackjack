package blackjack.domain.gambler;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.game.Round.BLACKJACK_CARD_COUNT;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;
import java.util.Objects;

public class Player {
    private final Name name;
    private final Hands hands = new Hands();

    public Player(final Name name) {
        this.name = name;
    }

    public void hit(final Card card) {
        hands.addCard(card);
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public boolean isBust() {
        return hands.isScoreExceed(BLACKJACK);
    }

    public boolean isBlackjack() {
        return hands.hasSize(BLACKJACK_CARD_COUNT) && calculateScore() == BLACKJACK;
    }

    public boolean isNameEquals(final Name name) {
        return Objects.equals(name, this.name);
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public List<Card> getInitialCards() {
        return getCards();
    }
}
