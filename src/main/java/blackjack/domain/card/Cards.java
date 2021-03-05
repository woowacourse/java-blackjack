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

    public Result getOtherCardsCompareResult(Cards otherCards) {
        Result result = getBurstOrBlackjackCompareResult(otherCards);
        if (result != Result.NONE) {
            return result;
        }
        return getScoreCompareResult(otherCards);
    }

    private Result getBurstOrBlackjackCompareResult(Cards otherCards) {
        if (isBothBurst(otherCards) || isBothBlackjack(otherCards)) {
            return Result.DRAW;
        }
        if (otherCards.isBlackJack() || this.isBurst()) {
            return Result.WIN;
        }
        if (otherCards.isBurst() || this.isBlackJack()) {
            return Result.LOSE;
        }
        return Result.NONE;
    }

    private Result getScoreCompareResult(Cards otherCards) {
        if (this.getScore() < otherCards.getScore()) {
            return Result.WIN;
        }
        if (this.getScore() > otherCards.getScore()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    private static boolean containAceCard(List<Card> cards) {
        return cards.stream()
            .anyMatch(Card::isAce);
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

    private boolean isBothBurst(Cards cards) {
        return this.isBurst() && cards.isBurst();
    }

    private boolean isBothBlackjack(Cards cards) {
        return this.isBlackJack() && cards.isBlackJack();
    }

}
