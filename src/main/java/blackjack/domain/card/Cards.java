package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int UPPER_ACE_BONUS_SCORE = 10;
    private static final int UPPER_ACE_LIMIT_SCORE = 11;

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    private int calculate() {
        int score = cards.stream()
                .mapToInt(Card::value)
                .sum();

        if (isContainAce() && score <= UPPER_ACE_LIMIT_SCORE) {
            return score + UPPER_ACE_BONUS_SCORE;
        }

        return score;
    }

    private boolean isContainAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int add(Card card) {
        cards.add(card);
        return calculate();
    }

    public int size() {
        return cards.size();
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> cards(int count) {
        return Collections.unmodifiableList(cards.stream()
                .limit(count)
                .collect(Collectors.toList()));
    }
}
