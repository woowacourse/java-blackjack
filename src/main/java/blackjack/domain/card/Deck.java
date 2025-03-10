package blackjack.domain.card;

import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("남아있는 카드가 없습니다.");
        }
        return cards.pop();
    }

    public void shuffleCards(CardsShuffler cardsShuffler) {
        cardsShuffler.shuffleCards(cards);
    }
}
