package blackjack.controller;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsScoreDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.WinningResultDto;
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

    public void run() {
        final BlackjackGame blackjackGame = initGame();

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame);
    }

    private BlackjackGame initGame() {
        try {
            final BlackjackGame blackjackGame = new BlackjackGame(inputView.readPlayerNames());
            final List<ParticipantCardsDto> participantCardsDtos = blackjackGame.init();

            outputView.printStartCards(participantCardsDtos);
            return blackjackGame;
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return initGame();
        }
    }

    private void playGame(final BlackjackGame blackjackGame) {
        final List<String> participantName = blackjackGame.getPlayersName();

        for (String name : participantName) {
            runPlayerTurn(blackjackGame, name);
        }

        final int count = blackjackGame.giveDealerMoreCard();
        outputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame, final String name) {
        boolean isFirst = true;

        while (blackjackGame.addCardToPlayers(name) && needMoreCard(name)) {
            showPlayerCards(blackjackGame, name);
            isFirst = false;
        }

        if (isFirst) {
            showPlayerCards(blackjackGame, name);
        }
    }

    private void showPlayerCards(final BlackjackGame blackjackGame, final String name) {
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
        final List<ParticipantCardsScoreDto> participantCardsScoreDto = blackjackGame.getFinalResults();
        final WinningResultDto winningResults = blackjackGame.getWinningResults();
        outputView.printFinalResult(participantCardsScoreDto, winningResults);
    }
}
