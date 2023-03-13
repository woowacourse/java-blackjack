package blackjack.controller;

import blackjack.domain.player.*;
import blackjack.dto.PlayerDto;
import blackjack.service.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.stream.Collectors;

public final class Controller {

    public void run() {
        try {
            final Names names = Names.from(InputView.readPlayerNames());
            final Participants participants = createParticipants(names);
            final Dealer dealer = Dealer.create();
            final BlackjackGame blackjackGame = BlackjackGame.create();

            initGame(blackjackGame, participants, dealer);
            distributeCards(blackjackGame, participants, dealer);

            printPlayerCardsAndScore(participants, dealer);
            printRevenue(blackjackGame, participants, dealer);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private Participants createParticipants(final Names names) {
        return Participants.from(names.getNames()
                .stream()
                .map(this::createParticipant)
                .collect(Collectors.toList()));
    }

    private Participant createParticipant(final String name) {
        return Participant.from(name, BetAmount.from(InputView.readBetValue(name)));
    }

    private void initGame(final BlackjackGame blackjackGame, final Participants participants, final Dealer dealer) {
        OutputView.printSetupGame(participants.getNames());
        blackjackGame.distributeInitialCards(participants, dealer);
        printInitialCard(participants, dealer);
    }

    private void printInitialCard(final Participants participants, final Dealer dealer) {
        printPlayerCards(dealer);
        participants.getParticipants()
                .forEach(this::printPlayerCards);
    }

    private void printPlayerCards(final Player player) {
        OutputView.printPlayerCards(PlayerDto.of(player.getName(), player.getCardNames()));
    }

    private void distributeCards(final BlackjackGame blackjackGame, final Participants participants, final Dealer dealer) {
        for (Participant participant : participants.getParticipants()) {
            playParticipantTurn(blackjackGame, participant);
        }
        playDealerTurn(blackjackGame, dealer);
    }

    private void playParticipantTurn(final BlackjackGame blackjackGame, final Participant participant) {
        while (participant.isInPlaying() && isHit(participant)) {
            blackjackGame.distributeCard(participant);
            OutputView.printPlayerCards(PlayerDto.of(participant.getName(), participant.getCardNames()));
        }
    }

    private boolean isHit(final Participant participant) {
        return InputView.readCommand(PlayerDto.of(participant.getName(), participant.getCardNames()));
    }

    private void playDealerTurn(final BlackjackGame blackjackGame, final Dealer dealer) {
        blackjackGame.distributeCard(dealer);

        while (dealer.isInPlaying()) {
            blackjackGame.distributeCard(dealer);
            OutputView.printDealerHit();
        }

        OutputView.printLineSeparator();
    }

    private void printPlayerCardsAndScore(final Participants participants, final Dealer dealer) {
        printParticipantCards(dealer);
        participants.getParticipants()
                .forEach(this::printParticipantCards);
    }

    private void printParticipantCards(final Player player) {
        OutputView.printPlayerScore(PlayerDto.of(player.getName(), player.getCardNames(), player.getScore()));
    }

    private void printRevenue(final BlackjackGame blackjackGame, final Participants participants, final Dealer dealer) {
        OutputView.printGameResult(blackjackGame.calculateRevenues(participants, dealer));
    }
}
