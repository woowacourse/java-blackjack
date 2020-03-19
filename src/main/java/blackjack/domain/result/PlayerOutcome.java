package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PlayerOutcome {
    WIN("승", "패", PlayerOutcome::isWin, 1),
    DRAW("무", "무", PlayerOutcome::isDraw, 0),
    LOSE("패", "승", PlayerOutcome::isLose, -1),
    BLACKJACK("블랙잭", "패", PlayerOutcome::isBlackjack, 1.5);

    private final String name;
    private final String converseName;
    private final BiPredicate<CardsResult, CardsResult> compare;
    private final double profitRatio;

    PlayerOutcome(String name, String converseName, BiPredicate<CardsResult, CardsResult> compare, double profitRatio) {
        this.name = name;
        this.converseName = converseName;
        this.compare = compare;
        this.profitRatio = profitRatio;
    }

    public static PlayerOutcome of(CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        return Arrays.stream(values())
                .filter(outcome -> outcome.compare.test(playerCardsResult, dealerCardsResult))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isWin(CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        boolean dealerOnlyBust = !playerCardsResult.isBust() && dealerCardsResult.isBust();
        boolean playerNotBustAndMoreThanDealer =
                !playerCardsResult.isBust() && playerCardsResult.isMoreThanScore(dealerCardsResult);

        return dealerOnlyBust || playerNotBustAndMoreThanDealer;
    }

    private static boolean isDraw(CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        return playerCardsResult.equals(dealerCardsResult) && !playerCardsResult.isBust();
    }

    private static boolean isLose(CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        boolean dealerOnlyBlackJack =
                !playerCardsResult.isBlackJack() && dealerCardsResult.isBlackJack();

        return dealerOnlyBlackJack || dealerCardsResult.isMoreThanScore(playerCardsResult)
                || playerCardsResult.isBust();
    }

    private static boolean isBlackjack(CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        return playerCardsResult.isBlackJack() && !dealerCardsResult.isBlackJack();
    }

    public String getName() {
        return name;
    }

    public String getConverseName() {
        return converseName;
    }
}
