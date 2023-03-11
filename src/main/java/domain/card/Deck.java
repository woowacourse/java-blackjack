package domain.card;

import domain.card.shuffler.CardShuffler;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {

    private final LinkedList<Card> unusedCards;

    private Deck(final List<Card> unusedCards) {
        this.unusedCards = new LinkedList<>(unusedCards);
    }

    public static Deck from(CardShuffler cardShuffler) {
        List<Card> cards = Card.getInitializedCards();
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
