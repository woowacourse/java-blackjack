package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    private static boolean containAceCard(List<Card> cards) {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return getScore() == BLACKJACK_NUMBER && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean isBurst() {
        return getScore() > BLACKJACK_NUMBER;
    }

    public int getScore() {
        int score = 0;

        if (containAceCard(cards)) {
            score = calculate(10);
        }
        if (score != 0 && score <= BLACKJACK_NUMBER) {
            return score;
        }
        return calculate();
    }

    private int calculate() {
        return cards.stream()
            .mapToInt(Card::findDenominationValue)
            .sum();
    }

    private int calculate(int bonusScore) {
        return cards.stream()
            .mapToInt(Card::findDenominationValue)
            .sum() + bonusScore;
    }

}
