package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_VALUE = 21;
    private static final int ACE_ADVANTAGE_VALUE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBurst() {
        return calculateSumOfCards() > BLACKJACK_VALUE;
    }

    public boolean isBlackjack() {
        return calculateSumOfCards() == BLACKJACK_VALUE;
    }
    
    public String getFirstCardName() {
        return cards.getFirst().getName();
    }

    public List<String> getCardsName() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public int calculateSumOfCards() {
        int sum = cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();
        return applyBestAceValue(sum);
    }

    private int applyBestAceValue(int sum) {
        if (hasAce() && (sum + ACE_ADVANTAGE_VALUE) <= BLACKJACK_VALUE) {
            return sum + ACE_ADVANTAGE_VALUE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

}
