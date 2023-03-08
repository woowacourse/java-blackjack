package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.Queue;

public class Deck {

    public static final int NUMBER_OF_CARDS_IN_DECK = 52;

    private final Queue<Card> cards;

    public Deck(final Queue<Card> cards) {
        validateCards(cards);
        this.cards = cards;
    }

    private void validateCards(final Queue<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("카드에 null 이 들어왔습니다");
        }
        if (cards.size() != NUMBER_OF_CARDS_IN_DECK) {
            throw new IllegalArgumentException("카드 숫자가 " + NUMBER_OF_CARDS_IN_DECK + "장이 아닙니다");
        }
    }

    public Card popCard() {
        return cards.remove();
    }

}
