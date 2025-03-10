package domain;

import java.util.Set;
import java.util.Stack;

public class Deck {
    private final Stack<Card> deck = new Stack<>();

    public Deck(Shuffler shuffler) {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        shuffler.shuffle(deck);
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 뽑을 수 있는 카드가 없습니다.");
        }
        return deck.pop();
    }

    public CardHand getInitialDeal() {
        Card firstCard = drawCard();
        Card secondCard = drawCard();
        return new CardHand(Set.of(firstCard, secondCard));
    }
}
