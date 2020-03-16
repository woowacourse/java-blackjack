package blackjack.domain.card;

import java.util.Stack;

public class CardFactory {
    public static Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();
        createBySuit(cards);
        return cards;
    }

    private static void createBySuit(Stack<Card> cards) {
        for (Suit suit : Suit.values()) {
            createByRank(cards, suit);
        }
    }

    private static void createByRank(Stack<Card> cards, final Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.push(Card.from(suit, rank));
        }
    }
}
