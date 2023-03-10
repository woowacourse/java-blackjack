package balckjack.domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private static final int BLACKJACK_SCORE = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;

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
        Score dealerScore = dealerDeck.calculateScore();
        int dealerCardsCount = dealerDeck.findCardsCount();
        return playersDeck.stream()
            .map((deck) -> compareScore(dealerScore.getValue(), dealerCardsCount,
                deck.calculateScore().getValue(), deck.findCardsCount()))
            .collect(Collectors.toList());
    }

    private Result compareScore(int dealerScore, int dealerCardsCount, int playerScore,
        int playerCardCount) {
        if (isBlackJack(playerScore, playerCardCount)) {
            if (isBlackJack(dealerScore, dealerCardsCount)) {
                return Result.DRAW;
            }
            return Result.BLACKJACK;
        }
        if (isBlackJack(dealerScore, dealerCardsCount) || playerScore > BLACKJACK_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE || playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private boolean isBlackJack(int score, int count) {
        return score == BLACKJACK_SCORE && count == BLACKJACK_CARD_COUNT;
    }

    public Double calculateDealerWinningMoney() {
        return -winningMoneys.stream().mapToDouble(Double::doubleValue).sum();
    }

    public Map<String, Long> countDealerResult() {
        return results.stream().collect(groupingBy(Result::getResult, counting()));
    }

    public List<Double> calculateWinningMoneys() {
        return winningMoneys;
    }
}
