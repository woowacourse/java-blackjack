package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    public static final Name DEALER_NAME = new Name("딜러");

    private final Player player = new Player(DEALER_NAME);

    public void addCard(final Card card) {
        player.addCard(card);
    }

    public int calculateScore() {
        return player.calculateScore();
    }

    public boolean isScoreBelow(final int criteria) {
        return player.isScoreBelow(criteria);
    }

    public int calculateScoreDifference(final Player player) {
        return calculateScore() - player.calculateScore();
    }

    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    public boolean isNameEquals(final Name name) {
        return player.isNameEquals(name);
    }

    public Name getName() {
        return player.getName();
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
