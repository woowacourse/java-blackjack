package blackjack.domain.io;

import blackjack.domain.game.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameInputOutput {

    private final DealerAndPlayersFunction printInitialHandsMethod;
    private final Function<String, Boolean> readWannaHitMethod;
    private final PlayerFunction printHitResultMethod;
    private final Consumer<Integer> printDealerDrawingMethod;
    private final DealerAndPlayersFunction printFinalHandsMethod;
    private final Consumer<GameResult> printGameResultMethod;

    public GameInputOutput(
            DealerAndPlayersFunction printInitialHandsMethod,
            Function<String, Boolean> readWannaHitMethod,
            PlayerFunction printHitResultMethod,
            Consumer<Integer> printDealerDrawingMethod,
            DealerAndPlayersFunction printFinalHandsMethod,
            Consumer<GameResult> printGameResultMethod
    ) {
        this.printInitialHandsMethod = printInitialHandsMethod;
        this.readWannaHitMethod = readWannaHitMethod;
        this.printHitResultMethod = printHitResultMethod;
        this.printDealerDrawingMethod = printDealerDrawingMethod;
        this.printFinalHandsMethod = printFinalHandsMethod;
        this.printGameResultMethod = printGameResultMethod;
    }

    public void printInitialHands(Dealer dealer, List<Player> players) {
        printInitialHandsMethod.execute(dealer, players);
    }

    public boolean readIngWannaHit(String nickname) {
        return readWannaHitMethod.apply(nickname);
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

    public void printGameResult(GameResult gameResult) {
        printGameResultMethod.accept(gameResult);
    }
}
