package blackjack.domain.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;

public enum GameResult {

    WIN,
    LOSE,
    DRAW,
    BLACKJACK;

    public static GameResult getGameResult(Dealer dealer, Gambler gambler) {
        GameScore gamblerScore = gambler.getGameScore();
        GameScore dealerScore = dealer.getGameScore();
        return determineResultAfterBustCheck(dealerScore, gamblerScore);
    }

    private static GameResult determineResultAfterBustCheck(GameScore gamblerScore, GameScore dealerScore) {
        if (gamblerScore.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore.isBust()) {
            return GameResult.WIN;
        }
        return checkIsBlackjack(gamblerScore, dealerScore);
    }

    private static GameResult checkIsBlackjack(GameScore gamblerScore, GameScore dealerScore) {
        if (gamblerScore.isBlackJack() && dealerScore.isBlackJack()) {
            return GameResult.DRAW;
        }
        if (gamblerScore.isBlackJack()) {
            return GameResult.BLACKJACK;
        }
        return compareScores(gamblerScore, dealerScore);
    }

    private static GameResult compareScores(GameScore gamblerScore, GameScore dealerScore) {
        if (gamblerScore.isBiggerThan(dealerScore)) {
            return GameResult.WIN;
        }
        if (gamblerScore.isLessThan(dealerScore)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}

