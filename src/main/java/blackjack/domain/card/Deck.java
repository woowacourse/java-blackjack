package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
    }

    public static Deck of(CardsFactory cardsFactory, Shuffler shuffler) {
        List<Card> generatedCards = cardsFactory.generate();
        List<Card> shuffledCards = shuffler.shuffle(generatedCards);
        return new Deck(shuffledCards);
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("덱에 모든 카드가 소진되었습니다.");
        }
        return deck.remove(deck.size() - 1);
    }
}
