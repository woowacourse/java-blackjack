package blackjack.domain.io;

import blackjack.domain.game.PlayerProfit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.value.BettingAmount;
import blackjack.domain.value.Nickname;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameInputOutput {

    private final BiConsumer<Dealer, List<Player>> printInitialHandsMethod;
    private final Function<Nickname, Boolean> readWannaHitMethod;
    private final Function<Nickname, BettingAmount> readBettingAmountMethod;
    private final Consumer<Player> printHitResultMethod;
    private final Consumer<Integer> printDealerDrawingMethod;
    private final BiConsumer<Dealer, List<Player>> printFinalHandsMethod;
    private final Consumer<List<PlayerProfit>> printPlayerProfitsMethod;

    public GameInputOutput(
            BiConsumer<Dealer, List<Player>> printInitialHandsMethod,
            Function<Nickname, Boolean> readWannaHitMethod,
            Function<Nickname, BettingAmount> readBettingAmountMethod,
            Consumer<Player> printHitResultMethod,
            Consumer<Integer> printDealerDrawingMethod,
            BiConsumer<Dealer, List<Player>> printFinalHandsMethod,
            Consumer<List<PlayerProfit>> printPlayerProfitsMethod
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
        printInitialHandsMethod.accept(dealer, players);
    }

    public boolean readIngWannaHit(Nickname nickname) {
        return readWannaHitMethod.apply(nickname);
    }

    public BettingAmount readBettingAmount(Nickname nickname) {
        return readBettingAmountMethod.apply(nickname);
    }

    public void printingHitResult(Player player) {
        printHitResultMethod.accept(player);
    }

    public void printDealerDrawing(int count) {
        printDealerDrawingMethod.accept(count);
    }

    public void printFinalHands(Dealer dealer, List<Player> players) {
        printFinalHandsMethod.accept(dealer, players);
    }

    public void printPlayerProfits(List<PlayerProfit> playerProfits) {
        printPlayerProfitsMethod.accept(playerProfits);
    }
}
