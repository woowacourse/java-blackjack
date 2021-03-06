package blackjack.domain.participants;

import blackjack.domain.PlayerCards;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    private static final int BUST_LIMIT = 22;

    private final String name;
    private final PlayerCards playerCards;

    public Participant(final String name) {
        this.name = name;
        this.playerCards = new PlayerCards();
    }

    public void receiveCard(final Card card) {
        playerCards.receiveCard(card);
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return playerCards.getCardCount();
    }

    public List<Card> getPlayerCards() {
        return playerCards.toList();
    }

    public boolean isBust() {
        return calculate() >= BUST_LIMIT;
    }

    public int calculate() {
        return playerCards.calculate();
    }

    public abstract List<Card> showCards();

    public abstract boolean checkMoreCardAvailable();
}
