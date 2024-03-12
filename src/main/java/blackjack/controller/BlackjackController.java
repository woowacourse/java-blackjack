package blackjack.controller;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinningResult;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.WinningResultDto;
import blackjack.service.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runBlackjack() {
        final BlackjackGame blackjackGame = readyGame();

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame);
    }

    private BlackjackGame readyGame() {
        try {
            final BlackjackGame blackjackGame = new BlackjackGame(inputView.readPlayerNames());
            final List<ParticipantCardsDto> participantCardsDtos = blackjackGame.getStartCards();

            outputView.printStartCards(participantCardsDtos);
            return blackjackGame;
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readyGame();
        }
    }

    private void playGame(final BlackjackGame blackjackGame) {
        final List<String> playersName = blackjackGame.getPlayersName();

        for (String name : playersName) {
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

    private void finishGame(final BlackjackGame blackjackGame) {
        outputView.printFinalResult(getScoreResult(blackjackGame), getWinningResult(blackjackGame));
    }

    private static WinningResultDto getWinningResult(final BlackjackGame blackjackGame) {
        final WinningResult winningResult = blackjackGame.getWinningResult();

        return WinningResultDto.of(winningResult.getParticipantsResult(), winningResult.summarizeDealerResult());
    }

    private static List<ParticipantScoreDto> getScoreResult(final BlackjackGame blackjackGame) {
        Map<ParticipantName, Hands> handResult = blackjackGame.getHandResult();
        Map<ParticipantName, Score> scoreResult = blackjackGame.getScoreResult();

        return handResult.entrySet().stream()
                .map(entry -> ParticipantScoreDto.of(entry.getKey(), entry.getValue(), scoreResult.get(entry.getKey())))
                .toList();
    }
}
