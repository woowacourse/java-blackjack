package blackjack.model;

public class Referee {

    public GameResult judge(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        GameResult blackjackResult = blackjackJudgement(player, dealer);
        if (blackjackResult != null) {
            return blackjackResult;
        }

        GameResult bustResult = bustJudgement(player, dealer);
        if (bustResult != null) {
            return bustResult;
        }

        GameResult scoreResult = ScoreJudgement(playerScore, dealerScore);
        if (scoreResult != null) {
            return scoreResult;
        }

        return GameResult.WIN;
    }

    private GameResult ScoreJudgement(Score playerScore, Score dealerScore) {
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
