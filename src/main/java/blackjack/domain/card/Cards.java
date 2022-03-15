package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {

    private static final int ACE_ADDITIONAL_NUMBER = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final String DUPLICATE_EXCEPTION_MESSAGE = "카드 패에 중복된 카드가 존재할 수 없습니다.";

    private final List<Card> cards;

    public Cards(List<Card> cardHand) {
        validateDuplicate(cardHand);
        this.cards = new ArrayList<>(cardHand);
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public void concat(Cards cards) {
        this.cards.addAll(cards.getCards());
        new Cards(this.cards);
    }

    public int getBestPossible() {
        int sum = getLowestSum();

        for (Card card : cards) {
            sum = updateSum(sum, card);
        }

        return sum;
    }

    private int updateSum(int sum, Card card) {
        if (card.isAce() && sum + ACE_ADDITIONAL_NUMBER <= BUST_THRESHOLD) {
            sum += ACE_ADDITIONAL_NUMBER;
        }
        return sum;
    }

    private int getLowestSum() {
        return cards.stream()
                .map(Card::getNumber)
                .map(Number::getScore)
                .reduce(0, Integer::sum);
    }

    public boolean isBusted() {
        return getBestPossible() > BUST_THRESHOLD;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && containsAce() && containsJQK();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private boolean containsJQK() {
        return cards.stream().anyMatch(Card::isJQK);
    }

    public List<Card> getCards() {
        return cards;
    }
}
