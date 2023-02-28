package blackjack.domain;

import java.util.Queue;

public class Deck {

    public static final int NUMBER_OF_CARDS_IN_DECK = 52;

    private final Queue<Card> cards;

    Deck(final Queue<Card> cards) { //생성자, 정적팩토리메서드(로직이 있는 경우) , 팩토리 패턴(연쇄적 )
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
//        Card card = cards.poll(); illegal(커스텀메시지 넣기 위해서) or NoSuchException ***
    }
}
