package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

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
        int sum = cards.stream()
                .mapToInt(TrumpCard::getCardNumber)
                .sum();
        if (hasAce() && sum <= 11) {
            sum += 10;
        }
        return sum > 21;
    }
}
