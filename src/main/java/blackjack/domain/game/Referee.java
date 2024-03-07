package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class Referee {

    private final Dealer dealer;

    public Referee(Dealer dealer) {
        this.dealer = dealer;
    }


    public GameResult judgeGameResult(Player player) {
        // TODO: 함수 분리
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (player.getScore() > dealer.getScore() ||
                player.isBlackjack() ||
                dealer.isBurst()) {
            return GameResult.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
