package domain.card;

import domain.card.strategy.DrawStrategy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Deck {

    private final DrawStrategy drawStrategy;
    private final Deque<TrumpCard> deck;

    public Deck(DrawStrategy drawStrategy) {
        this.deck = generateDeck();
        this.drawStrategy = drawStrategy;
    }

    public TrumpCard drawCard() {
        return drawStrategy.draw(deck);
    }

    private LinkedList<TrumpCard> generateDeck() {
        List<Suit> suits = Arrays.stream(Suit.values()).toList();
        Set<TrumpCard> trumpCards = new HashSet<>();
        for (Suit suit : suits) {
            trumpCards.addAll(trumpCards(suit));
        }
        LinkedList<TrumpCard> generatedDeck = new LinkedList<>(trumpCards);
        Collections.shuffle(generatedDeck);
        return generatedDeck;
    }

    private static Set<TrumpCard> trumpCards(Suit suit) {
        List<CardValue> cardValues = CardValue.cardValues();
        return cardValues.stream().map(cardValue -> new TrumpCard(suit, cardValue))
                .collect(Collectors.toSet());
    }
}
