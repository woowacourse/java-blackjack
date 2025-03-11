package blackjack.domain.card;

import java.util.Stack;

public class DeckFactory {

    public static Deck createShuffledDeck(CardsShuffler cardsShuffler) {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffleCards(cards, cardsShuffler);
        return new Deck(cards);
    }

    public static void shuffleCards(Stack<Card> cards, CardsShuffler cardsShuffler) {
        cardsShuffler.shuffleCards(cards);
    }
}
