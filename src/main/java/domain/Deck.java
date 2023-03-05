package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        generateCards();
    }

    private void generateCards() {
        for (Suit suit : Suit.values()){
            addCardsWithSymbolOf(suit);
        }
        Collections.shuffle(this.deck);
    }

    private void addCardsWithSymbolOf(Suit suit){
        for (Rank rank : Rank.values()){
            this.deck.add(new Card(suit, rank));
        }
    }

    public Card draw() {
        try {
            return this.deck.remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
    }
}
