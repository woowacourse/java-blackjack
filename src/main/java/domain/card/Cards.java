package domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final String ACE_LETTER = "A";
    public static final int ACE_ADDITIONAL_VALUE = 10;
    public static final int BLACKJACK_MAX_VALUE_CRITERIA = 21;

    private final List<Card> cards;

    public Cards(List<Card> init) {
        cards = init;
    }

    public int calculateSum() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public int calculateResult() {
        if (hasAce()) {
            return judgeAdvantageResult(calculateSum());
        }
        return calculateSum();
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(card -> card.getDenomination().equals(ACE_LETTER));
    }

    private int judgeAdvantageResult(int sumAsOne) {
        int sumAsEleven = sumAsOne + ACE_ADDITIONAL_VALUE;

        int gapByEleven = Math.abs(BLACKJACK_MAX_VALUE_CRITERIA - sumAsEleven);
        int gapByOne = Math.abs(BLACKJACK_MAX_VALUE_CRITERIA - sumAsOne);
        
        if (gapByEleven < gapByOne) {
            return sumAsEleven;
        }
        return sumAsOne;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
