package domain;

import java.util.List;
import java.util.Set;

public class Deck {
    private final List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public Card drawNewCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 생성할 수 없습니다.");
        }
        return deck.removeFirst();
    }

    public CardHand getInitialDeal() {
        Card firstCard = drawNewCard();
        Card secondCard = drawNewCard();
        return new CardHand(Set.of(firstCard, secondCard));
    }
}
