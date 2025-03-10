package blackjack.mock;

import blackjack.domain.game.GameInputOutput;
import blackjack.dto.FinalHands;
import blackjack.dto.HandState;
import blackjack.dto.InitialHands;
import blackjack.dto.WinningState;

public class GameInputOutputMock extends GameInputOutput {

    private InitialHands initialHands;
    private HandState handState;
    private int dealerDrawingCount;
    private FinalHands finalHands;
    private WinningState winningState;

    public GameInputOutputMock() {
        super(null, null, null,
                null, null, null);
    }

    @Override
    public void executePrintInitialHands(InitialHands initialHands) {
        this.initialHands = initialHands;
    }

    @Override
    public boolean executeReadIngWannaHit(String nickname) {
        return true;
    }

    @Override
    public void executePrintingHitResult(HandState state) {
        this.handState = state;
    }

    @Override
    public void executePrintDealerDrawing(int count) {
        this.dealerDrawingCount = count;
    }

    @Override
    public void executePrintFinalHands(FinalHands finalHands) {
        this.finalHands = finalHands;
    }

    @Override
    public void executePrintGameResult(WinningState winningState) {
        this.winningState = winningState;
    }

    public InitialHands getInitialHands() {
        return initialHands;
    }

    public HandState getHandState() {
        return handState;
    }

    public int getDealerDrawingCount() {
        return dealerDrawingCount;
    }

    public FinalHands getFinalHands() {
        return finalHands;
    }

    public WinningState getWinningState() {
        return winningState;
    }
}
