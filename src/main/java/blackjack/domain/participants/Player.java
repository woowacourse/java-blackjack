package blackjack.domain.participants;

import static blackjack.domain.BlackjackConstants.BURST_THRESHOLD;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
    }

    public void send(Card... cards) {
        this.cards.take(cards);
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }

    public boolean canSend() {
        int minScore = cards.calculateMinScore();
        return minScore < BURST_THRESHOLD.getSymbol();
    }
}
