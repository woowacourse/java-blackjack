package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int UPPER_ACE_ADDABLE_VALUE = 11;

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    private int calculate() {
        int score = sum();

        if (canAddAceUpperValue(score)) {
            score -= Denomination.ACE.getValue();
            score += Denomination.ACE.getAceUpperValue();
        }

        return score;
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    private boolean canAddAceUpperValue(int score) {
        return cards.stream()
                .anyMatch(Card::isAce) && score <= UPPER_ACE_ADDABLE_VALUE;
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
