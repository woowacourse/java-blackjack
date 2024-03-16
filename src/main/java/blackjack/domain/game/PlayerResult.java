package blackjack.domain.game;

import blackjack.domain.Ownable;
import blackjack.domain.betting.Money;
import blackjack.domain.betting.OwnedMoney;
import blackjack.domain.participant.Player;

public class PlayerResult implements Ownable<Player> {

    private final Player player;
    private final GameResult gameResult;

    public PlayerResult(Player player, GameResult gameResult) {
        this.player = player;
        this.gameResult = gameResult;
    }

    //TODO TDA
    public Money calculatePrize(OwnedMoney bet) {
        return gameResult.calculatePrize(bet.getMoney());
    }

    @Override
    public Player getOwner() {
        return player;
    }
}
