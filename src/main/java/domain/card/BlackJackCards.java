package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackCards {

    private static final int BURST_UPPER_BOUND = 21;
    private static final int BLACK_JACK_SIZE = 2;

    private final List<Card> cards;

    public BlackJackCards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBustBy(int value) {
        return value > BURST_UPPER_BOUND;
    }

    public boolean isBlackJack() {
        return getTotalValue() == BURST_UPPER_BOUND && cards.size() == BLACK_JACK_SIZE;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getTotalValue() {
        final int constTotalValue = calculateConstValue(cards);
        final int aceCount = getAceCount();
        return calculateTotalValue(constTotalValue, aceCount);
    }

    /***
     * 반복문을 돌며 A가 11로 계산될 수 있는지에 대한 경우의 수를 탐색한다.
     */
    private int calculateTotalValue(int baseValue, int aceCount) {
        int candidateResult = baseValue;
        for (int oneValueCount = 0; oneValueCount <= aceCount; ++oneValueCount) {
            candidateResult = baseValue + (oneValueCount * 1) + ((aceCount - oneValueCount) * 11);
            if (isBustBy(candidateResult)) {
                continue;
            }
            return candidateResult;
        }
        return candidateResult;
    }

    private int calculateConstValue(List<Card> cards) {
        return cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(Card::getValue)
            .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }
}
