package blackJack.domain.Card;


import java.util.*;

import static blackJack.utils.ExeptionMessage.NO_MORE_CARD;

public class Deck {
    public final LinkedList<Card> deck;

    public static LinkedList<Card> initDeck(){
        LinkedList<Card> allCards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            Arrays.stream(Denomination.values())
                    .forEach(denomination -> allCards.add(new Card(suit,denomination)));
        }
        Collections.shuffle(allCards);
        return new LinkedList<>(allCards);
    }

    public Deck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public Card getCard() {
        validateEmpty(this.deck);
        return deck.poll();
    }

    private void validateEmpty(LinkedList<Card> deck){
        if(deck.size() == 0){
            throw new IllegalArgumentException(NO_MORE_CARD);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck1 = (Deck) o;
        return Objects.equals(deck, deck1.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }
}
