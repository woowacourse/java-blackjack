package controller;

import controller.dto.HandStatus;
import domain.BlackJackGame;
import domain.Participant;
import domain.Player;
import domain.constants.CardCommand;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class GameManager {
    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = start();
        rotate(blackJackGame);
        blackJackGame.finish();
    }

    private BlackJackGame start() {
        BlackJackGame blackJackGame = BlackJackGame.from(inputView.enterPlayerNames());
        outputView.printInitialStatus(blackJackGame.initialize());
        return blackJackGame;
    }

    private void rotate(final BlackJackGame blackJackGame) {
        List<Participant> participants = blackJackGame.getParticipants();
        for (Participant participant : participants) {
            blackJackGame.giveCard(
                    participant,
                    getAction(participant),
                    getSupplier(participant)
            );
        }
    }

    private Consumer<HandStatus> getAction(final Participant participant) {
        if (participant instanceof Player) {
            return handStatus -> outputView.printCardStatus(handStatus);
        }
        return handStatus -> outputView.printDealerPickMessage();
    }

    private Supplier<CardCommand> getSupplier(final Participant participant) {
        if (participant instanceof Player) {
            return () -> CardCommand.from(
                    inputView.decideToGetMoreCard(participant.getName())
            );
        }
        return () -> CardCommand.HIT;
    }
}
