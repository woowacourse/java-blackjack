package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Participants participants = getParticipants();
            getBetAmounts(participants);
            BlackjackGame blackjackGame = new BlackjackGame(participants);
            startGame(blackjackGame, participants);
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception.getMessage());
        }

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

    private void getBetAmounts(Participants participants) {
        for (Player player : participants.getPlayers()) {
            getBetAmountEachPlayer(player);
        }
    }

    private void getBetAmountEachPlayer(Player player) {
        try {
            int betAmount = inputView.readBetAmount(player.getName());
            player.initBetAmount(betAmount);
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception.getMessage());
            getBetAmountEachPlayer(player);
        }
    }

    private void startGame(BlackjackGame blackjackGame, Participants participants) {
        blackjackGame.dealOutCard();
        printInitGame(participants);
        play(participants, blackjackGame);
        printResult(blackjackGame, participants);
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
        while (player.canHit() && command.isPlay()) {
            command = getCommand(player);
            giveCard(player, blackjackGame, command);
            outputView.printPlayerCards(player.getName(), player.getCardNames());
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

    private void printInitGame(Participants participants) {
        outputView.printInitCardsMessage(participants.getPlayerNames());

        Dealer dealer = participants.getDealer();
        outputView.printDealerInitCards(dealer.getName(), dealer.getCardNames()
                                                                .get(0));
        for (Player player : participants.getPlayers()) {
            outputView.printPlayerCards(player.getName(), player.getCardNames());
        }
    }

    private void printResult(BlackjackGame blackjackGame, Participants participants) {
        printCardResult(participants);
        printGameResult(blackjackGame, participants);
    }

    private void printCardResult(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            outputView.printEachParticipantCardsResult(
                    participant.getName(), participant.getCardNames(), participant.getScore());
        }
    }

    private void printGameResult(BlackjackGame blackjackGame, Participants participants) {
        outputView.printGameResultMessage();
        GameResult gameResult = blackjackGame.getResult();
        outputView.printDealerGameResult(gameResult.getDealerWinCount(),
                gameResult.getDealerLoseCount(), gameResult.getDealerDrawCount());
        for (Player player : participants.getPlayers()) {
            outputView.printEachPlayerGameResult(player.getName(),
                    gameResult.getResultStateByPlayer(player));
        }
    }
}
