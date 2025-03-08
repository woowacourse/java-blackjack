package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private static final Stack<Card> DEFAULT_CARDS = new Stack<>();

    private final Stack<Card> cards;

    static {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                DEFAULT_CARDS.push(new Card(suit, rank));
            }
        }
    }

    public Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck defaultDeck() {
        return new Deck(DEFAULT_CARDS);
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
        List<Card> shuffledCard = new ArrayList<>();
        while (!cards.isEmpty()) {
            shuffledCard.add(cards.pop());
        }
        cardsShuffler.shuffle(shuffledCard);
        Collections.reverse(shuffledCard);
        cards.addAll(shuffledCard);
    }
}
