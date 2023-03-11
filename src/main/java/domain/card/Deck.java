package domain.card;

import domain.card.shuffler.CardShuffler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {

    private static final List<Card> CARDS;

    private final LinkedList<Card> unusedCards;

    static {
        CARDS = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            int denominationLength = Denomination.values().length;
            addCard(suit, denominationLength);
        }
    }

    private static void addCard(final Suits suit, final int denominationLength) {
        for (int i = 0; i < denominationLength; i++) {
            Deck.CARDS.add(new Card(Denomination.values()[i], suit));
        }
    }

    private Deck(final List<Card> unusedCards) {
        this.unusedCards = new LinkedList<>(unusedCards);
    }

    public static Deck from(CardShuffler cardShuffler) {
        List<Card> cards = new ArrayList<>(CARDS);
        cardShuffler.shuffleCards(cards);
        return new Deck(cards);
    }

    public Card pickCard() {
        try {
            return unusedCards.remove();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("더이상 뽑을 카드가 없습니다.");
        }
    }
}
