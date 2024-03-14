package blackjack.domain.game.result;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerLose implements GameResult {

    private static final PlayerLose instance = new PlayerLose();

    private PlayerLose() {
    }

    //TODO 라인 길이 수정
    @Override
    public boolean isSatisfy(Dealer dealer, Player player) {
        if (player.isBusted()) {
            return true;
        }
        if (player.isBlackjack()) {
            return false;
        }
        if (dealer.isBlackjack()) {
            return true;
        }
        if (dealer.isBusted()) {
            return false;
        }
        return player.calculateScore() < dealer.calculateScore();
    }

    @Override
    public Money calculatePrize(Money bet) {
        return bet.multiply(-1);
    }

    public static PlayerLose getInstance() {
        return instance;
    }
}
