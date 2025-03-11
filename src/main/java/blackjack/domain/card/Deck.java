package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Deck {

    private static final Deque<Card> DEFAULT_CARDS = new ArrayDeque<>();

    private final Deque<Card> cards;

    static {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                DEFAULT_CARDS.push(new Card(suit, rank));
            }
        }
    }

    public Deck(Card... cards) {
        this(new ArrayDeque<>(List.of(cards)));
    }

    public Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck defaultDeck() {
        return new Deck(new ArrayDeque<>(DEFAULT_CARDS));
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
        cards.addAll(shuffledCard);
    }
}
