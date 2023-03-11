package domain.player;

import domain.card.Deck;
import domain.card.Score;
import view.AddCardCommand;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Dealer extends Player {
    private static final Score DEALER_GIVE_CARD_STATE_MAX_SCORE = new Score(16);
    
    public Dealer() {
        super("딜러");
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        throw new IllegalStateException("딜러는 수익률 계산을 할 수 없습니다.");
    }
    
    @Override
    public boolean isFinished() {
        return getTotalScore().isOverThen(DEALER_GIVE_CARD_STATE_MAX_SCORE);
    }
    
    @Override
    public void drawOrFinishBy(
            Deck deck, Function<Player,
            AddCardCommand> ignore,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        if (isFinished()) {
            return;
        }
    
        draw(deck.draw());
        printParticipantCardStatus.accept(Collections.emptyList());
    
        if (isDealerNotFinished()) {
            drawStop();
        }
    }
    
    private boolean isDealerNotFinished() {
        return !isFinished();
    }
}
