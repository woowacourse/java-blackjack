package model.card;

import java.util.List;

public final class Deck {
    private final List<Card> deck;

    public Deck(final List<Card> deck) {
        this.deck = deck;
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 모두 소진했습니다.");
        }
        return deck.removeLast();
    }
//
//    public CardHand getInitialDeal() {
//        Card firstCard = receiveCard();
//        Card secondCard = receiveCard();
//        return new CardHand(Set.of(firstCard, secondCard));
//    }
}
