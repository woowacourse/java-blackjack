package blackjack.model;

import java.util.List;

public class Referee {

    private final List<JudgementRule> rules;

    public Referee() {
        this.rules = List.of(
                this::blackjackJudgement,
                this::bustJudgement,
                this::scoreJudgement
        );
    }

    public GameResult judge(Player player, Dealer dealer) {
        for (JudgementRule rule : rules) {
            GameResult result = rule.apply(player, dealer);
            if (result != null) {
                return result;
            }
        }
        return GameResult.WIN;
    }

    @FunctionalInterface
    public interface JudgementRule {
        GameResult apply(Player player, Dealer dealer);
    }

    private GameResult scoreJudgement(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        if (playerScore.isSame(dealerScore)) {
            return GameResult.DRAW;
        }
        if (playerScore.isLess(dealerScore)) {
            return GameResult.LOSE;
        }
        return null;
    }

    private GameResult bustJudgement(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return null;
    }

    private GameResult blackjackJudgement(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return GameResult.LOSE;
        }
        return null;
    }
}
