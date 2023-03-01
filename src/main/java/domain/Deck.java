package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final CardIndexGenerator cardIndexGenerator;
    private final List<Card> unusedCards;

    private Deck(CardIndexGenerator cardIndexGenerator, List<Card> unusedCards) {
        this.cardIndexGenerator = cardIndexGenerator;
        this.unusedCards = new LinkedList<>(unusedCards);
    }

    public static Deck from(final CardIndexGenerator cardIndexGenerator) {
        List<Card> cards = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            int denominationLength = Denomination.values().length;
            for (int i = 0; i < denominationLength; i++) {
                cards.add(new Card(Denomination.values()[i], suit));
            }
        }
        return new Deck(cardIndexGenerator, cards);
    }

    public Card pickCard() {
        int index = cardIndexGenerator.chooseIndex(unusedCards.size());
        return unusedCards.remove(index);
    }
}
