package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantCardsResultDto;
import blackjack.dto.ParticipantGameResultDto;
import blackjack.dto.PlayerNamesDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

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
        return retryWhenException(() -> {
            List<String> names = inputView.readNames();
            return Participants.from(names);
        });
    }

    private void getBetAmounts(Participants participants) {
        for (Player player : participants.getPlayers()) {
            getBetAmountEachPlayer(player);
        }
    }

    private void getBetAmountEachPlayer(Player player) {
        retryWhenException(() -> {
            int betAmount = inputView.readBetAmount(player.getName());
            player.initBetAmount(betAmount);
            return player;
        });
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
        return retryWhenException(() -> {
            String inputCommand = inputView.readIsContinue(player.getName());
            return GameCommand.from(inputCommand);
        });
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

    private <T> T retryWhenException(Supplier<T> supplier) {
        T result;
        do {
            result = getSupplier(supplier);
        } while (result == null);

        return result;
    }

    private <T> T getSupplier(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception.getMessage());
            return null;
        }
    }
}
