package balckjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Referee {

    private final List<Result> results;
    private final List<Money> moneys;

    public Referee(CardDeck dealerDeck, List<CardDeck> playersDeck, List<Money> moneys) {
        this.results = initializeResults(dealerDeck, playersDeck);
        this.moneys = moneys;
    }

    private List<Result> initializeResults(CardDeck dealerDeck, List<CardDeck> playersDeck) {
        return playersDeck.stream()
            .map((deck) -> compareScore(dealerDeck, deck))
            .collect(Collectors.toList());
    }

    private Result compareScore(CardDeck dealerDeck, CardDeck playerDeck) {
        Score dealerScore = dealerDeck.calculateScore();
        Score playerScore = playerDeck.calculateScore();

        return judgeResult(dealerDeck, playerDeck, dealerScore, playerScore);
    }

    private Result judgeResult(CardDeck dealerDeck, CardDeck playerDeck, Score dealerScore,
        Score playerScore) {
        if (isDraw(dealerDeck, playerDeck, dealerScore, playerScore)) {
            return Result.DRAW;
        }
        if (playerDeck.isBlackJack()) {
            return Result.BLACKJACK;
        }
        if (isWin(dealerScore, playerScore)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private boolean isDraw(CardDeck dealerDeck, CardDeck playerDeck, Score dealerScore,
        Score playerScore) {
        return
            (!playerScore.isBust()
                && !dealerDeck.isBlackJack()
                && !playerDeck.isBlackJack()
                && playerScore.equals(dealerScore))
                || (dealerDeck.isBlackJack() && playerDeck.isBlackJack());
    }

    private boolean isWin(Score dealerScore, Score playerScore) {
        return !playerScore.isBust() && (dealerScore.isBust()
            || playerScore.isMoreThan(dealerScore));
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
