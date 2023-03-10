package balckjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Referee {

    private final List<Double> winningMoneys;
    private List<Result> results;

    public Referee(CardDeck dealerDeck, List<CardDeck> playersDeck, List<Money> moneys) {
        results = generateResult(dealerDeck, playersDeck);

        winningMoneys = new ArrayList<>();
        for (int i = 0; i < playersDeck.size(); i++) {
            winningMoneys.add(moneys.get(i).applyRate(results.get(i)));
        }
    }

    private List<Result> generateResult(CardDeck dealerDeck, List<CardDeck> playersDeck) {
        return playersDeck.stream()
            .map((deck) -> compareScore(dealerDeck, deck))
            .collect(Collectors.toList());
    }

    private Result compareScore(CardDeck dealerDeck, CardDeck playerDeck) {
        Score dealerScore = dealerDeck.calculateScore();
        Score playerScore = playerDeck.calculateScore();

        if (playerDeck.isBlackJack()) {
            if (dealerDeck.isBlackJack()) {
                return Result.DRAW;
            }
            return Result.BLACKJACK;
        }
        if (dealerDeck.isBlackJack() || playerScore.isMoreThan(Score.BLACKJACK_SCORE)) {
            return Result.LOSE;
        }
        if (dealerScore.isMoreThan(Score.BLACKJACK_SCORE) || playerScore.isMoreThan(dealerScore)) {
            return Result.WIN;
        }
        if (playerScore.equals(dealerScore)) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public Double calculateDealerWinningMoney() {
        return -winningMoneys.stream().mapToDouble(Double::doubleValue).sum();
    }

    public List<Double> calculateWinningMoneys() {
        return winningMoneys;
    }
}
