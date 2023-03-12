package blackjack.controller;

import blackjack.domain.*;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantCardsResultDto;
import blackjack.dto.ParticipantGameResultDto;
import blackjack.dto.PlayerNamesDto;
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
            outputView.printPlayerCards(ParticipantCardsDto.from(player));
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
        outputView.printInitCardsMessage(PlayerNamesDto.from(participants.getPlayers()));

        Dealer dealer = participants.getDealer();
        outputView.printDealerInitCards(ParticipantCardsDto.from(dealer));
        for (Player player : participants.getPlayers()) {
            outputView.printPlayerCards(ParticipantCardsDto.from(player));
        }
    }

    private void printResult(BlackjackGame blackjackGame, Participants participants) {
        printCardResult(participants);
        printGameResult(blackjackGame, participants);
    }

    private void printCardResult(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            outputView.printEachParticipantCardsResult(ParticipantCardsResultDto.of(participant));
        }
    }

    private void printGameResult(BlackjackGame blackjackGame, Participants participants) {
        outputView.printGameResultMessage();
        blackjackGame.calculateBetAmount();
        Dealer dealer = participants.getDealer();
        outputView.printEachParticipantGameResult(ParticipantGameResultDto.of(dealer));
        for (Player player : participants.getPlayers()) {
            outputView.printEachParticipantGameResult(ParticipantGameResultDto.of(player));
        }
    }
}
