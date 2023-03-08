package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public final class ShuffledDeck implements Deck {
    private final Deque<Card> deck = new ArrayDeque<>();

    public ShuffledDeck() {
        generateCards();
    }

    private void generateCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()){
            addCardsWithSymbolOf(suit, cards);
        }
        Collections.shuffle(cards);
        this.deck.addAll(cards);
    }

    private void addCardsWithSymbolOf(Suit suit, List<Card> cards){
        for (Rank rank : Rank.values()){
            cards.add(new Card(suit, rank));
        }
    }

    @Override
    public Card draw() {
        try {
            return this.deck.pop();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
    }
}
