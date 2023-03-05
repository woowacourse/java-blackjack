package blackjack.domain.deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import blackjack.domain.card.Card;

public class Deck {
    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generate();
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> drawCards(int size) {
        List<Card> poppedCards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            poppedCards.add(cards.pop());
        }
        return poppedCards;
    }
}
