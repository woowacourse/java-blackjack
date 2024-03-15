package blackjack.controller;

import blackjack.domain.BettingResult;
import blackjack.domain.BettingResultDtos;
import blackjack.domain.BettingResults;
import blackjack.domain.PlayerBettings;
import blackjack.domain.participant.Players;
import blackjack.domain.result.WinningResult;
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

    public void runBlackjack() { // TODO: 메서드 분리, 재입력 받기
        Players players = Players.from(inputView.readPlayerNames());
        PlayerBettings playerBettings = PlayerBettings.from(inputView.readBettings(players.getNames()));
//            Map<String, Integer> playerBettings = PlayerBetting.from(inputView.readBettings(players.getNames()));

        final BlackjackGame blackjackGame = readyGame(players);

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame, playerBettings);
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

    private void playGame(final BlackjackGame blackjackGame) {
        final List<String> playerNames = blackjackGame.getPlayersNames();

        for (String name : playerNames) {
            runPlayerTurn(blackjackGame, name);
        }

        final int count = blackjackGame.giveDealerMoreCards();
        outputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame, final String name) {
        boolean isFirst = true;

        while (blackjackGame.addCardToPlayers(name) && needMoreCard(name)) {
            printPlayerCards(blackjackGame, name);
            isFirst = false;
        }

        if (isFirst) {
            printPlayerCards(blackjackGame, name);
        }
    }

    private void printPlayerCards(final BlackjackGame blackjackGame, final String name) {
        final List<CardDto> cards = blackjackGame.getCardsOf(name);
        outputView.printPlayerCard(new ParticipantCardsDto(name, cards));
    }

    private boolean needMoreCard(final String name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private void finishGame(final BlackjackGame blackjackGame, final PlayerBettings playerBettings) {
        final ParticipantScoresDto participantScoresDto = ParticipantScoresDto.of(blackjackGame.getHandResult(), blackjackGame.getScoreResult());

        final WinningResult winningResult = blackjackGame.getWinningResult(); // 딜러 처리 빼든지

        BettingResults bettingResults = BettingResults.of(playerBettings, winningResult);
        BettingResult dealerResult = bettingResults.getDealerResult();
        BettingResultDtos bettingResultDtos = BettingResultDtos.of(bettingResults, dealerResult);
//        final WinningResultDto winningResultDto = WinningResultDto.of(winningResult.getParticipantsResult(), winningResult.summarizeDealerResult());

        outputView.printFinalResult(participantScoresDto, bettingResultDtos);
    }
}
