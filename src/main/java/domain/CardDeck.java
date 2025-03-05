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

    public void hasAce() {

    }

    public boolean checkOverScore() {
        return cards.stream()
                .mapToInt(TrumpCard::getCardNumber).sum() > 21;
    }
}
