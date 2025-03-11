package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static GameResult getGameResult(Dealer dealer, Gambler gambler) {
        if (isGamblerLoser(dealer, gambler)) {
            return GameResult.LOSE;
        }
        if (isGamblerWinner(dealer, gambler)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private static boolean isGamblerWinner(Dealer dealer, Gambler gambler) {
        if (dealer.isPlayerNotBust() && gambler.isPlayerNotBust()) {
            return dealer.calculateCardNumber() < gambler.calculateCardNumber();
        }
        return dealer.isPlayerBust() && gambler.isPlayerNotBust();
    }

    private static boolean isGamblerLoser(Dealer dealer, Gambler gambler) {
        if (dealer.isPlayerNotBust() && gambler.isPlayerNotBust()) {
            return dealer.calculateCardNumber() > gambler.calculateCardNumber();
        }
        return dealer.isPlayerNotBust() && gambler.isPlayerBust();
    }
}

