package domain;

import static util.ExceptionConstants.ERROR_HEADER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO : 딜러와 참여자의 로직을 분리
public class Cards {
    private static final int VALID_MAX_SUM_LIMIT = 21;
    private static final int VALID_DRAW_LIMIT = 16;
    private static final String MAX_SUM_EXCEED_ERROR = "카드의 합이 21을 초과하였습니다.";
    private static final int ACE_CONVERSION_THRESHOLD = 10;
    private static final int DOUBLE_ACE_COUNT = 2;
    private static final int SINGLE_ACE_COUNT = 1;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void checkMaxSum() {
        if(calculateSumWithLowAce() > VALID_MAX_SUM_LIMIT) {
            throw new IllegalArgumentException(ERROR_HEADER + MAX_SUM_EXCEED_ERROR);
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addAll(Cards receivedCards) {
        this.cards.addAll(receivedCards.getCards());
    }

    public boolean isUnderDrawLimit() {
        if (isAceCountEqualTo(DOUBLE_ACE_COUNT)) {
            return true;
        }
        int sumWithoutAce = calculateSumWithoutAce();
        if (isAceCountEqualTo(SINGLE_ACE_COUNT)) {
            return (sumWithoutAce + CardNumberType.getAceHighNumber()) <= VALID_DRAW_LIMIT;
        }
        return sumWithoutAce <= VALID_DRAW_LIMIT;
    }

    private boolean isAceCountEqualTo(int targetCount) {
        int aceCount = (int)cards.stream()
                .filter(card -> card.cardNumberType().isAce())
                .count();
        return aceCount == targetCount;
    }

    public int calculateSumResult() {
        if(hasNotAce()) {
            return calculateSumWithoutAce();
        }
        int cardsSumWithoutAce = calculateSumWithoutAce();
        int aceCount = calculateAceCount();

        return calculateSumWithAces(aceCount, cardsSumWithoutAce);
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    private int calculateSumWithAces(int aceCount, int cardsWithoutAceSum) {
        int result = cardsWithoutAceSum;
        for (int i = 0; i < aceCount; i++) {
            if(result <= ACE_CONVERSION_THRESHOLD) {
                result += CardNumberType.getAceHighNumber();
                continue;
            }
            result += CardNumberType.getAceLowNumber();
        }
        return result;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(card -> card.cardNumberType() == CardNumberType.ACE)
                .count();
    }

    private int calculateSumWithoutAce() {
        return cards.stream()
                .filter(card -> card.cardNumberType() != CardNumberType.ACE)
                .mapToInt(card -> card.cardNumberType().getDefaultNumber())
                .sum();
    }

    private int calculateSumWithLowAce() {
        return cards.stream()
                .map(Card::cardNumberType)
                .mapToInt(CardNumberType::getDefaultNumber)
                .sum();
    }

    private boolean hasNotAce() {
        return cards.stream()
                .noneMatch(card -> card.cardNumberType() == CardNumberType.ACE);
    }
}
