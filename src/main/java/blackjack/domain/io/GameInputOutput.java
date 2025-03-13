package blackjack.domain.io;

import blackjack.domain.game.PlayerProfits;
import blackjack.domain.user.BettingAmount;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameInputOutput {

    private final DealerAndPlayersFunction printInitialHandsMethod;
    private final Function<String, Boolean> readWannaHitMethod;
    private final Function<String, BettingAmount> readBettingAmountMethod;
    private final PlayerFunction printHitResultMethod;
    private final Consumer<Integer> printDealerDrawingMethod;
    private final DealerAndPlayersFunction printFinalHandsMethod;
    private final Consumer<PlayerProfits> printPlayerProfitsMethod;

    public GameInputOutput(
            DealerAndPlayersFunction printInitialHandsMethod,
            Function<String, Boolean> readWannaHitMethod,
            Function<String, BettingAmount> readBettingAmountMethod,
            PlayerFunction printHitResultMethod,
            Consumer<Integer> printDealerDrawingMethod,
            DealerAndPlayersFunction printFinalHandsMethod,
            Consumer<PlayerProfits> printPlayerProfitsMethod
    ) {
        this.printInitialHandsMethod = printInitialHandsMethod;
        this.readWannaHitMethod = readWannaHitMethod;
        this.readBettingAmountMethod = readBettingAmountMethod;
        this.printHitResultMethod = printHitResultMethod;
        this.printDealerDrawingMethod = printDealerDrawingMethod;
        this.printFinalHandsMethod = printFinalHandsMethod;
        this.printPlayerProfitsMethod = printPlayerProfitsMethod;
    }

    public void printInitialHands(Dealer dealer, List<Player> players) {
        printInitialHandsMethod.execute(dealer, players);
    }

    public boolean readIngWannaHit(String nickname) {
        return readWannaHitMethod.apply(nickname);
    }

    public BettingAmount readBettingAmount(String nickname) {
        return readBettingAmountMethod.apply(nickname);
    }

    public void printingHitResult(Player player) {
        printHitResultMethod.execute(player);
    }

    public void printDealerDrawing(int count) {
        printDealerDrawingMethod.accept(count);
    }

    public void printFinalHands(Dealer dealer, List<Player> players) {
        printFinalHandsMethod.execute(dealer, players);
    }

    public void printPlayerProfits(PlayerProfits playerProfits) {
        printPlayerProfitsMethod.accept(playerProfits);
    }
}
