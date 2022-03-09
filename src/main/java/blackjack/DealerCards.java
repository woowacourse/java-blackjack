package blackjack;

import java.util.List;


// ACE를 11로 처리해서 점수 계산
public class DealerCards {

    private final List<Card> cards;

    public DealerCards(Card... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        int value = 0;
        for (Card card : cards) {
            value += valueForDealer(card);
        }
        return new Score(value);
    }

    private int valueForDealer(Card card) {
        if (card.isAce()) {
            return 11;
        }
        return card.getRank();
    }
}
