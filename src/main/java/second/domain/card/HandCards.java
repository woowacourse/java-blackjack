package second.domain.card;

import second.domain.ICardDeck;
import second.domain.score.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCards {
    private static final int FIRST_INDEX = 0;
    private final List<Card> cards;

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    public HandCards() {
        this(new ArrayList<>());
    }

    public void drawCard(final Card drawCard) {
        cards.add(drawCard);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateDefaultSum() {
        return cards.stream()
                .mapToInt(Card::extractScore)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getOneCard() {
        return cards.get(FIRST_INDEX);
    }
}
