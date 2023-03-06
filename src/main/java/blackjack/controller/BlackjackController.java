package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = getParticipants();
        BlackjackGame blackjackGame = new BlackjackGame(participants);
        startGame(participants, blackjackGame);
        printResult(blackjackGame);
    }

    private void startGame(Participants participants, BlackjackGame blackjackGame) {
        blackjackGame.dealOutCard();
        outputView.printInitCards(participants);
        play(participants, blackjackGame);
        outputView.printCardResult(participants);
    }

    private void printResult(BlackjackGame blackjackGame) {
        Map<Player, GameResult> result = blackjackGame.getResult();
        outputView.printGameResult(result);
    }

    private Participants getParticipants() {
        try {
            List<String> names = inputView.readNames();
            return Participants.from(names);
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception.getMessage());
            return getParticipants();
        }
    }

    private void play(Participants participants, BlackjackGame blackjackGame) {
        for (Player player : participants.getPlayers()) {
            playEachPlayer(player, blackjackGame);
        }
        Dealer dealer = participants.getDealer();
        playDealer(dealer, blackjackGame);
    }

    private void playEachPlayer(Player player, BlackjackGame blackjackGame) {
        GameCommand command = GameCommand.PLAY;
        while (!player.isBust() && command.isPlay()) {
            command = getCommand(player);
            giveCard(player, blackjackGame, command);
            outputView.printPlayerCards(player);
        }
    }

    private GameCommand getCommand(Player player) {
        try {
            String inputCommand = inputView.readIsContinue(player.getName());
            return GameCommand.from(inputCommand);
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception.getMessage());
            return getCommand(player);
        }
    }

    private void giveCard(Player player, BlackjackGame blackjackGame, GameCommand command) {
        if (command.isPlay()) {
            blackjackGame.giveCard(player);
        }
    }

    private void playDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isHit()) {
            outputView.printDealerState();
            blackjackGame.giveCard(dealer);
        }
    }
}
