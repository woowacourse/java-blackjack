package blackjack.domain.card;

import blackjack.domain.rule.CardCalculator;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("카드 리스트를 생성할 수 없습니다.");
        }
        hand.add(card);
    }

    public int calculateSum() {
        return CardCalculator.calculate(hand);
    }

    public List<Card> getCardStatus() {
        return hand;
    }
}
