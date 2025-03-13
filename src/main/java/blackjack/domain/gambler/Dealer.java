package blackjack.domain.gambler;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer implements Gambler {
    public static final Name DEALER_NAME = new Name("딜러");

    private final Player player;

    public Dealer(final Player player) {
        this.player = player;
    }

    @Override
    public void addCard(final Card card) {
        player.addCard(card);
    }

    @Override
    public int calculateScore() {
        return player.calculateScore();
    }

    @Override
    public boolean isScoreBelow(final int criteria) {
        return player.isScoreBelow(criteria);
    }

    @Override
    public int calculateScoreDifference(final Gambler other) {
        return player.calculateScoreDifference(other);
    }

    @Override
    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    @Override
    public boolean isNameEquals(final Name name) {
        return player.isNameEquals(name);
    }

    @Override
    public Name getName() {
        return player.getName();
    }

    @Override
    public List<Card> getCards() {
        return player.getCards();
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = getCards();
        Card firstCard = cards.getFirst();
        return List.of(firstCard);
    }
}
