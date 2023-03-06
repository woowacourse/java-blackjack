package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private static final List<Card> CARDS;

    private final CardIndexGenerator cardIndexGenerator;
    private final List<Card> unusedCards;

    static {
        List<Card> cards = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            int denominationLength = Denomination.values().length;
            addCard(cards, suit, denominationLength);
        }
        CARDS = List.copyOf(cards);
    }

    public Deck(CardIndexGenerator cardIndexGenerator) {
        this.cardIndexGenerator = cardIndexGenerator;
        this.unusedCards = new LinkedList<>(CARDS);
    }

    private static void addCard(final List<Card> cards, final Suits suit, final int denominationLength) {
        for (int i = 0; i < denominationLength; i++) {
            cards.add(new Card(Denomination.values()[i], suit));
        }
    }

    public Card pickCard() {
        int index = cardIndexGenerator.chooseIndex(unusedCards.size());
        return unusedCards.remove(index);
    }
}
