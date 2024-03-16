package blackjack.controller;

import blackjack.domain.betting.PlayerBettings;
import blackjack.domain.participant.Players;
import blackjack.dto.BettingResultDtos;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoresDto;
import blackjack.service.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runBlackjack() {
        final Players players = createPlayers();
        final PlayerBettings playerBettings = createBettings(players);

        final BlackjackGame blackjackGame = readyGame(players);

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame, playerBettings);
    }

    private Players createPlayers() {
        try {
            return Players.from(inputView.readPlayerNames());
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createPlayers();
        }
    }

    private PlayerBettings createBettings(final Players players) {
        try {
            final Map<String, Integer> playerBettings = inputView.readBettings(players.getNames());
            return PlayerBettings.from(playerBettings);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createBettings(players);
        }
    }

    private BlackjackGame readyGame(final Players players) {
        try {
            final BlackjackGame blackjackGame = new BlackjackGame(players);
            blackjackGame.divideCard();
            final List<ParticipantCardsDto> participantCardsDtos = blackjackGame.getStartCards();

            outputView.printStartCards(participantCardsDtos);
            return blackjackGame;
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readyGame(players);
        }
    }

    private void playGame(final BlackjackGame blackjackGame) { // name -> player 방식 고민 마저
        for (Player player : blackjackGame.getPlayers()) {
            runPlayerTurn(blackjackGame, player);
        }

        int count = blackjackGame.giveDealerMoreCards();
        outputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame, final Player player) {
        boolean isFirst = true;

        while (blackjackGame.addCardToPlayer(player) && needMoreCard(player)) {
            printPlayerCards(player);
            isFirst = false;
        }

        if (isFirst) {
            printPlayerCards(player);
        }
    }

    private boolean needMoreCard(final String name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private boolean needMoreCard(final Player player) {
        ParticipantName rawName = player.getName();
        String name = rawName.getName();

        try {
            return inputView.readNeedMoreCard(name);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private void printPlayerCards(final Player player) {
        outputView.printPlayerCard(ParticipantCardsDto.from(player));
    }

    private void finishGame(final BlackjackGame blackjackGame, final PlayerBettings playerBettings) {
        final ParticipantScoresDto participantScoresDto = ParticipantScoresDto.of(blackjackGame.getHandResult(),
                blackjackGame.getScoreResult());

        final PlayerBettings bettingResults = playerBettings.applyWinStatus(blackjackGame.getWinningResult());
        final BettingResultDtos bettingResultDtos = BettingResultDtos.of(bettingResults);

        outputView.printFinalResult(participantScoresDto, bettingResultDtos);
    }
}
