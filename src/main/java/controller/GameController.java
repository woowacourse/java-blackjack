package controller;

import domain.game.BettingMoney;
import domain.game.BettingResults;
import domain.game.BlackjackGame;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import dto.DealerHandStatusDto;
import dto.PlayerHandStatusDto;
import dto.PlayingCardDto;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class GameController {

    public void run() {
        final BlackjackGame blackjackGame = initGame();
        final BettingResults bettingResults = playGame(blackjackGame);

        OutputView.printBettingResults(bettingResults);
    }

    private BlackjackGame initGame() {
        final BlackjackGame blackjackGame = BlackjackGame.init(initPlayer());
        printInitGameStatus(blackjackGame);

        return blackjackGame;
    }

    private Map<PlayerName, BettingMoney> initPlayer() {
        final PlayerNames playerNames = inputPlayerNames();

        return playerNames.values()
                .stream()
                .collect(toMap(playerName -> playerName, this::inputBettingMoney));
    }

    private PlayerNames inputPlayerNames() {
        try {
            final List<String> inputPlayerNames = InputView.inputPlayerNames();

            return PlayerNames.of(inputPlayerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());

            return inputPlayerNames();
        }
    }

    private BettingMoney inputBettingMoney(final PlayerName playerName) {
        try {
            final int inputBettingMoney = InputView.inputBettingMoney(playerName);

            return new BettingMoney(inputBettingMoney);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            return inputBettingMoney(playerName);
        }
    }

    private void printInitGameStatus(final BlackjackGame blackjackGame) {
        final PlayingCardDto dealerCardDto = PlayingCardDto.of(blackjackGame.getDealer().getFirstPlayingCard());
        final List<PlayerHandStatusDto> playerHandStatusDtos = blackjackGame.getPlayers()
                .stream()
                .map(PlayerHandStatusDto::of)
                .toList();

        OutputView.printFirstDrawStatus(dealerCardDto, playerHandStatusDtos);
    }

    private BettingResults playGame(final BlackjackGame blackjackGame) {
        startPlayersTurn(blackjackGame);
        startDealerTurn(blackjackGame);
        printParticipantStatusesAfterDrawing(blackjackGame);

        return BettingResults.of(blackjackGame);
    }

    private void startPlayersTurn(final BlackjackGame blackjackGame) {
        while (blackjackGame.hasDrawablePlayer()) {
            blackjackGame.findDrawablePlayer()
                    .ifPresent(player -> playPlayerTurn(blackjackGame, player));
        }
    }

    private void playPlayerTurn(final BlackjackGame blackjackGame, final Player player) {
        final boolean isDraw = blackjackGame.drawPlayerIfAccept(player, inputDrawDecision(player.getPlayerName()));
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
        final DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(blackjackGame.getDealer());
        final List<PlayerHandStatusDto> playerHandStatusDtos = blackjackGame.getPlayers()
                .stream()
                .map(PlayerHandStatusDto::of)
                .toList();

        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
    }
}
