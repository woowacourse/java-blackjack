package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final int MAX_SCORE = 21;
    private static final int ACE_MAX = 11;
    private static final int ACE_MIN = 1;

    private final List<TrumpCard> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public void addTrumpCard(TrumpCard card) {
        cards.add(card);
    }

    public int cardsSize() {
        return cards.size();
    }

    public List<TrumpCard> getFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        return List.of(cards.getFirst());
    }

    public List<TrumpCard> getAllCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        return new ArrayList<>(cards);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch((card) -> card.getCardNumber() == CardNumber.ACE.getValue());
    }

    public boolean checkOverScore() {
        int sum = calculateScore();
        return sum > MAX_SCORE;
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(TrumpCard::getCardNumber)
                .sum();
        if (hasAce() && sum <= ACE_MAX) {
            sum += (ACE_MAX - ACE_MIN);
        }
        return sum;
    }
}
