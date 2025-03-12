package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;

import java.util.Optional;

public enum GameResult {

    WIN,
    LOSE,
    DRAW,
    BLACKJACK;

    public static GameResult getGameResult(Dealer dealer, Gambler gambler) {
        GameScore gamblerScore = gambler.getGameScore();
        GameScore dealerScore = dealer.getGameScore();

        Optional<GameResult> result = checkBust(gamblerScore, dealerScore);
        if (result.isPresent()) {
            return result.get();
        }
        result = checkBlackjack(gamblerScore, dealerScore);
        return result.orElseGet(() -> compareScores(gamblerScore, dealerScore));
    }

    private static Optional<GameResult> checkBust(GameScore gamblerScore, GameScore dealerScore) {
        if (gamblerScore.isBust()) {
            return Optional.of(GameResult.LOSE);
        }
        if (dealerScore.isBust()) {
            return Optional.of(GameResult.WIN);
        }
        return Optional.empty();
    }

    private static Optional<GameResult> checkBlackjack(GameScore gamblerScore, GameScore dealerScore) {
        if (gamblerScore.isBlackJack() && dealerScore.isBlackJack()) {
            return Optional.of(GameResult.DRAW);
        }
        if (gamblerScore.isBlackJack()) {
            return Optional.of(GameResult.BLACKJACK);
        }
        return Optional.empty();
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

