package domain.player;

import domain.card.Deck;
import domain.game.AddCardCommand;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        return getState().calculateProfit(betAmount);
    }
    
    @Override
    public boolean isFinished() {
        return getState().isFinished();
    }
    
    @Override
    public void drawOrFinishParticipantBy(
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        if (isFinished()) {
            printParticipantCardStatus.accept(List.of(this));
            return;
        }
        
        decideDrawOrFinish(deck, supplyCommand, printParticipantCardStatus);
    }
    
    private void decideDrawOrFinish(
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        AddCardCommand command = supplyCommand.apply(this);
        if (command.isAddCardCommand()) {
            draw(deck.draw());
        }
        printParticipantCardStatus.accept(List.of(this));
    
        if (command.isNotAddCardCommand() || isFinished()) {
            drawStopIfNotAddCardCommand(command);
            return;
        }
        decideDrawOrFinish(deck, supplyCommand, printParticipantCardStatus);
    }
    
    @Override
    public void drawOrFinishDealerBy(Deck deck, Consumer<List<Player>> printParticipantCardStatus) {
        throw new UnsupportedOperationException("참가자가 사용할 수 없는 기능입니다.");
    }
    
    @Override
    public double supplyBetAmount(ToDoubleFunction<String> supplyBetAmount) {
        return supplyBetAmount.applyAsDouble(getName());
    }
    
    private void drawStopIfNotAddCardCommand(AddCardCommand command) {
        if (command.isNotAddCardCommand()) {
            drawStop();
        }
    }
}
