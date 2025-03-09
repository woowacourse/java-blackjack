package blackjack.domain.game;

import blackjack.dto.FinalHands;
import blackjack.dto.HandState;
import blackjack.dto.InitialHands;
import blackjack.dto.WinningState;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameInputOutput {

    private final Consumer<InitialHands> printInitialHands;
    private final Function<String, Boolean> readWannaHit;
    private final Consumer<HandState> printHitResult;
    private final Consumer<Integer> printDealerDrawing;
    private final Consumer<FinalHands> printFinalHands;
    private final Consumer<WinningState> printGameResult;

    public GameInputOutput(
            Consumer<InitialHands> printInitialHands,
            Function<String, Boolean> readWannaHit,
            Consumer<HandState> printHitResult,
            Consumer<Integer> printDealerDrawing,
            Consumer<FinalHands> printFinalHands,
            Consumer<WinningState> printGameResult
    ) {
        this.printInitialHands = printInitialHands;
        this.readWannaHit = readWannaHit;
        this.printHitResult = printHitResult;
        this.printDealerDrawing = printDealerDrawing;
        this.printFinalHands = printFinalHands;
        this.printGameResult = printGameResult;
    }

    public void executePrintInitialHands(InitialHands initialHands) {
        printInitialHands.accept(initialHands);
    }

    public boolean executeReadIngWannaHit(String nickname) {
        return readWannaHit.apply(nickname);
    }

    public void executePrintingHitResult(HandState state) {
        printHitResult.accept(state);
    }

    public void executePrintDealerDrawing(int count) {
        printDealerDrawing.accept(count);
    }

    public void executePrintFinalHands(FinalHands finalHands) {
        printFinalHands.accept(finalHands);
    }

    public void executePrintGameResult(WinningState winningState) {
        printGameResult.accept(winningState);
    }
}

