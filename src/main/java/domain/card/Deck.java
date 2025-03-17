package domain.card;

import domain.card.strategy.DrawStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private static final List<TrumpCard> CACHE_CARDS = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            CACHE_CARDS.addAll(createCardsBySuit(suit));
        }
    }

    private final DrawStrategy drawStrategy;
    private final Deque<TrumpCard> deck;

    public Deck(DrawStrategy drawStrategy) {
        this.deck = generateDeck();
        this.drawStrategy = drawStrategy;
    }

    private static List<TrumpCard> createCardsBySuit(Suit suit) {
        return Arrays.stream(CardValue.values())
                .map(cardValue -> new TrumpCard(suit, cardValue))
                .toList();
    }

    private LinkedList<TrumpCard> generateDeck() {
        LinkedList<TrumpCard> generatedDeck = new LinkedList<>(CACHE_CARDS);
        Collections.shuffle(generatedDeck);
        return generatedDeck;
    }

    public TrumpCard drawCard() {
        return drawStrategy.draw(deck);
    }

}
