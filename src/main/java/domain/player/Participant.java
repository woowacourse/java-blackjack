package domain.player;

import domain.card.Deck;
import view.AddCardCommand;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
    public void drawOrFinishBy(
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
    
    private void drawStopIfNotAddCardCommand(AddCardCommand command) {
        if (command.isNotAddCardCommand()) {
            drawStop();
        }
    }
}
