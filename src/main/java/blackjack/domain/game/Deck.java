package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.Queue;

public class Deck {


    private final Queue<Card> cards;

    public Deck(Queue<Card> cards) {
        validateCards(cards);
        this.cards = cards;
    }

    private void validateCards(Queue<Card> cards) {
        if (cards == null) throw new IllegalArgumentException("카드에 null 이 들어왔습니다");
    }

    public Card popCard() {
        return cards.remove();
    }

}
