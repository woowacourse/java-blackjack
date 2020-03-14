package blackjack.domain.card;

import blackjack.domain.card.component.CardNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final String INDEX_ERROR_MSG = "카드에 잘못된 접근입니다.";
    private List<Card> cards;
    public static final int UPPER_LIMIT = 21;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int computeSum() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        return handleAce(sum);
    }

    private int handleAce(int sum) {
        int aceCount = (int) cards.stream()
                .filter(x -> x.has(CardNumber.ACE))
                .count();

        while (aceCount-- > 0) {
            if (UPPER_LIMIT < sum) {
                sum -= CardNumber.ACE_DIFF;
            }
        }
        return sum;
    }

    public List<String> getMessage() {
        return cards.stream()
                .map(Card::getMessage)
                .collect(Collectors.toList());
    }

    public Card getCard(int index) {
        if (index < 0 || index > cards.size()) {
            throw new IndexOutOfBoundsException(INDEX_ERROR_MSG);
        }
        return cards.get(index);
    }
}
