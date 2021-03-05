package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_NUMBER = 21;
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

    public Result compare(Cards cards) {
        if (getScore() < cards.getScore()) {
            return Result.WIN;
        }
        if (getScore() > cards.getScore()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackJack() {
        return getScore() == BLACKJACK_NUMBER
            && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public int getScore() {
        int score = 0;

        if (containAceCard(cards)) {
            score = calculate(10);
        }

        if (score != 0 && score <= 21) {
            return score;
        }

        return calculate();
    }

    private int calculate() {
        return cards.stream()
            .mapToInt(Card::value)
            .sum();
    }

    private int calculate(int bonusScore) {
        return cards.stream()
            .mapToInt(Card::value)
            .sum() + bonusScore;
    }

    public void add(Card card) {
        cards.add(card);
    }

}
