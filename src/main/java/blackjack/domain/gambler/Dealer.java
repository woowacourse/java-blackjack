package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    public static final Name DEALER_NAME = new Name("딜러");
    private static final int DEALER_DRAW_THRESHOLD = 17;

    private final Player player = new Player(DEALER_NAME);

    public void hit(final Card card) {
        player.hit(card);
    }

    public int calculateScore() {
        return player.calculateScore();
    }

    public boolean isBust() {
        return player.isBust();
    }

    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    public boolean isNameEquals(final Name name) {
        return player.isNameEquals(name);
    }

    public boolean mustDraw() {
        return player.calculateScore() < DEALER_DRAW_THRESHOLD;
    }

    public int calculateScoreDifference(final Player other) {
        return player.calculateScore() - other.calculateScore();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public List<Card> getInitialCards() {
        List<Card> cards = getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
