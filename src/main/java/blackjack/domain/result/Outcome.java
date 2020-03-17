package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    PLAYER_WIN("승", "패", Outcome::isPlayerWin),
    PLAYER_DRAW("무", "무", Outcome::isPlayerDraw),
    PLAYER_LOSE("패", "승", Outcome::isPlayerLose);

    private static boolean isPlayerWin(CardsResult playerCardsResult,
        CardsResult dealerCardsResult) {
        boolean playerOnlyBlackJack =
            playerCardsResult.isBlackJack() && !dealerCardsResult.isBlackJack();
        boolean dealerOnlyBust = !playerCardsResult.isBust() && dealerCardsResult.isBust();
        boolean playerNotBustAndMoreThanDealer =
            !playerCardsResult.isBust() && playerCardsResult.isMoreThanScore(dealerCardsResult);

        return playerOnlyBlackJack || dealerOnlyBust || playerNotBustAndMoreThanDealer;
    }

    private static boolean isPlayerDraw(
        CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        return playerCardsResult.equals(dealerCardsResult) && !playerCardsResult.isBust();
    }

    private static boolean isPlayerLose(
        CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        boolean dealerOnlyBlackJack =
            !playerCardsResult.isBlackJack() && dealerCardsResult.isBlackJack();

        return dealerOnlyBlackJack || dealerCardsResult.isMoreThanScore(playerCardsResult)
            || playerCardsResult.isBust();
    }

    private final String name;
    private final String converseName;
    private final BiPredicate<CardsResult, CardsResult> compare;

    Outcome(String name, String converseName, BiPredicate<CardsResult, CardsResult> compare) {
        this.name = name;
        this.converseName = converseName;
        this.compare = compare;
    }

    public static Outcome of(CardsResult playerCardsResult, CardsResult dealerCardsResult) {
        return Arrays.stream(values())
            .filter(outcome -> outcome.compare.test(playerCardsResult, dealerCardsResult))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return name;
    }

    public String getConverseName() {
        return converseName;
    }
}
