package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cards {

    private static final int ACE_ADDITION = 10;
    private static final int MAX_SCORE = 21;
    private static final int CARD_COUNT_FOR_BLACKJACK = 2;
    private static final List<Card> EMPTY_LIST = Collections.emptyList();

    private final List<Card> cards;

    private Cards(List<Card> initialCards) {
        cards = new ArrayList<>(initialCards);
    }

    public static Cards of(List<Card> cards) {
        return new Cards(cards);
    }

    public static Cards getEmptyCardsPack() {
        return new Cards(EMPTY_LIST);
    }

    public int calculateScore() {
        int aceAmount = countAceAmount();
        if (aceAmount != 0) {
            return judgeAdvantageResult(aceAmount);
        }
        return calculateSum();
    }

    private int countAceAmount() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private int judgeAdvantageResult(int aceAmount) {
        List<Integer> sumOfCases = new ArrayList<>();
        for (int aceCount = 1; aceCount <= aceAmount; aceCount++) {
            sumOfCases.add(sumAceAddition(calculateSum(), aceCount));
        }

        return sumOfCases.stream()
                .mapToInt(x -> x)
                .max()
                .orElse(calculateSum());
    }

    private int sumAceAddition(int sum, int aceCount) {
        int sumWithElevenAce = sum + aceCount * ACE_ADDITION;
        if (sumWithElevenAce > MAX_SCORE) {
            return sum;
        }
        return sumWithElevenAce;
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBust() {
        return calculateScore() > MAX_SCORE;
    }

    public boolean isBlackJack(){
        return cards.size() == CARD_COUNT_FOR_BLACKJACK && calculateScore() == MAX_SCORE;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card getCardByIndex(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
