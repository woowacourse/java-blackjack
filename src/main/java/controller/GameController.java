package controller;

import domain.game.BlackjackGame;
import domain.game.BlackjackGameResults;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import dto.DealerHandStatusDto;
import dto.PlayerHandStatusDto;
import dto.PlayingCardDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameController {

    public void run() {
        BlackjackGame blackjackGame = initGame();

        startPlayersTurn(blackjackGame);
        startDealerTurn(blackjackGame);
        printParticipantStatusesAfterDrawing(blackjackGame);

        BlackjackGameResults gameResults = BlackjackGameResults.of(blackjackGame);
        OutputView.printGameResult(gameResults);
    }

    private BlackjackGame initGame() {
        BlackjackGame blackjackGame = BlackjackGame.init(inputPlayerNames());
        PlayingCardDto dealerCardDto = PlayingCardDto.of(blackjackGame.getDealer().getFirstPlayingCard());
        List<PlayerHandStatusDto> playerHandStatusDtos = blackjackGame.getPlayers()
                .stream()
                .map(PlayerHandStatusDto::of)
                .toList();

        OutputView.printFirstDrawStatus(dealerCardDto, playerHandStatusDtos);
        return blackjackGame;
    }

    private PlayerNames inputPlayerNames() {
        try {
            List<String> inputPlayerNames = InputView.inputPlayerNames();
            return PlayerNames.of(inputPlayerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }

    private void startPlayersTurn(final BlackjackGame blackjackGame) {
        while (blackjackGame.hasDrawablePlayer()) {
            blackjackGame.findDrawablePlayer()
                    .ifPresent(player -> playPlayerTurn(blackjackGame, player));
        }
    }

    private void playPlayerTurn(final BlackjackGame blackjackGame, final Player player) {
        boolean isDraw = blackjackGame.drawPlayerIfAccept(player, inputDrawDecision(player.getPlayerName()));
        if (isDraw) {
            OutputView.printPlayerDrawStatus(PlayerHandStatusDto.of(player));
        }
    }

    private void startDealerTurn(final BlackjackGame blackjackGame) {
        while (blackjackGame.hasDrawableDealer()) {
            blackjackGame.playDealer();
            OutputView.printDealerDrawMessage();
        }
    }

    private boolean inputDrawDecision(PlayerName playerName) {
        try {
            return InputView.inputDrawDecision(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputDrawDecision(playerName);
        }
    }

    private void printParticipantStatusesAfterDrawing(final BlackjackGame blackjackGame) {
        DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(blackjackGame.getDealer());
        List<PlayerHandStatusDto> playerHandStatusDtos = blackjackGame.getPlayers()
                .stream()
                .map(PlayerHandStatusDto::of)
                .toList();

        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
    }
}
