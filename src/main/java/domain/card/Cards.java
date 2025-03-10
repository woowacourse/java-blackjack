package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_ADDITIONAL_SCORE = 10; // ACE는 1 또는 11 점수가 가능하다

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards of() {
        return new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        long aceCount = cards.stream()
                .filter(card -> card.getNumber() == TrumpNumber.ACE)
                .count();
        while (aceCount > 0 && sum + ACE_ADDITIONAL_SCORE <= Card.BLACKJACK_SCORE) {
            sum += ACE_ADDITIONAL_SCORE;
            aceCount--;
        }
        return sum;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
