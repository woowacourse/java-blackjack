package domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final String ACE_LETTER = "A";
    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_MAX_VALUE_CRITERIA = 21;

    private final List<Card> cards;

    public Cards(List<Card> initialCards) {
        cards = new ArrayList<>(initialCards);
    }

    public int calculateResult() {
        int aceAmount = countAceAmount();
        if (aceAmount != 0) {
            return judgeAdvantageResult(aceAmount);
        }
        return calculateSum();
    }

    private int judgeAdvantageResult(int aceAmount) {
        int result = calculateSum();

        for (int aceCount = 1;
            aceCount <= aceAmount && result + ACE_ADDITIONAL_VALUE <= BLACKJACK_MAX_VALUE_CRITERIA;
            aceCount++) {
            result += ACE_ADDITIONAL_VALUE;
        }
        return result;
    }

    public int calculateSum() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private int countAceAmount() {
        return (int) cards.stream()
            .filter(card -> card.getDenomination().equals(ACE_LETTER))
            .count();
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
