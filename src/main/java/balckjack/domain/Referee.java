package balckjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Referee {

    private final List<Result> results;
    private final List<Money> moneys;

    private Referee(List<Result> results, List<Money> moneys) {
        this.results = results;
        this.moneys = moneys;
    }
    public static Referee createJudged(CardDeck dealerDeck, List<CardDeck> playersDeck, List<Money> moneys){
        return new Referee(initializeResults(dealerDeck, playersDeck),moneys);
    }

    private static List<Result> initializeResults(CardDeck dealerDeck, List<CardDeck> playersDeck) {
        return playersDeck.stream()
            .map((deck) -> judgeResult(dealerDeck, deck))
            .collect(Collectors.toList());
    }

    private static Result judgeResult(CardDeck dealerDeck, CardDeck playerDeck) {
        if (isDraw(dealerDeck, playerDeck)) {
            return Result.DRAW;
        }
        if (playerDeck.isBlackJack()) {
            return Result.BLACKJACK;
        }
        if (isWin(dealerDeck, playerDeck)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private static boolean isDraw(CardDeck dealerDeck, CardDeck playerDeck) {
        return
            (!playerDeck.isBust()
                && !dealerDeck.isBlackJack()
                && !playerDeck.isBlackJack()
                && playerDeck.isEqualScore(dealerDeck))
                || (dealerDeck.isBlackJack() && playerDeck.isBlackJack());
    }

    private static boolean isWin(CardDeck dealerDeck, CardDeck playerDeck) {
        return !playerDeck.isBust() && (dealerDeck.isBust()
            || playerDeck.isMoreThan(dealerDeck));
    }

    public Double calculateDealerWinningMoney() {
        return -calculateWinningMoneys().stream().mapToDouble(Double::doubleValue).sum();
    }

    public List<Double> calculateWinningMoneys() {
        List<Double> winningMoneys = new ArrayList<>();
        for (int i = 0; i < moneys.size(); i++) {
            winningMoneys.add(
                moneys.get(i).applyRate(results.get(i)));
        }
        return winningMoneys;
    }
}
