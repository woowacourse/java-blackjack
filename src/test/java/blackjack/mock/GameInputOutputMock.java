package blackjack.mock;

import blackjack.domain.game.PlayerProfit;
import blackjack.domain.io.GameInputOutput;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.value.BettingAmount;
import java.util.List;

public class GameInputOutputMock extends GameInputOutput {

    public static final int BETTING_AMOUNT = 10000;

    public GameInputOutputMock() {
        super(null, null, null,
                null, null, null, null);
    }

    @Override
    public void printInitialHands(Dealer dealer, List<Player> players) {
    }

    @Override
    public boolean readIngWannaHit(String nickname) {
        return true;
    }

    @Override
    public BettingAmount readBettingAmount(String nickname) {
        return new BettingAmount(10000);
    }

    @Override
    public void printingHitResult(Player player) {
    }

    @Override
    public void printDealerDrawing(int count) {
    }

    @Override
    public void printFinalHands(Dealer dealer, List<Player> players) {
    }

    @Override
    public void printPlayerProfits(List<PlayerProfit> playerProfits) {
    }
}
