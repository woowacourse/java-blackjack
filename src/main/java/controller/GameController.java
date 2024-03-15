package controller;

import domain.game.*;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import dto.DealerHandStatusDto;
import dto.PlayerHandStatusDto;
import dto.PlayingCardDto;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableMap;

public class GameController {

    public void run() {
        PlayerNames playerNames = inputPlayerNames();
        BettingMoneyStore bettingMoneyStore = initBettingMoney(playerNames);
        BlackjackGame blackjackGame = initGame(playerNames);

        playGame(blackjackGame);
        printGameResults(blackjackGame, bettingMoneyStore);
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

    private BettingMoneyStore initBettingMoney(final PlayerNames playerNames) {
        Map<PlayerName, BettingMoney> bettingMoneyStore = playerNames.values()
                .stream()
                .collect(Collectors.toMap(
                        playerName -> playerName, this::inputBettingMoney,
                        (key, value) -> key,
                        HashMap::new
                ));

        return new BettingMoneyStore(unmodifiableMap(bettingMoneyStore));
    }

    private BettingMoney inputBettingMoney(final PlayerName playerName) {
        try {
            int inputBettingMoney = InputView.inputBettingMoney(playerName);
            return new BettingMoney(inputBettingMoney);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBettingMoney(playerName);
        }
    }

    private BlackjackGame initGame(final PlayerNames playerNames) {
        BlackjackGame blackjackGame = BlackjackGame.init(playerNames);
        PlayingCardDto dealerCardDto = PlayingCardDto.of(blackjackGame.getDealer().getFirstPlayingCard());
        List<PlayerHandStatusDto> playerHandStatusDtos = blackjackGame.getPlayers()
                .stream()
                .map(PlayerHandStatusDto::of)
                .toList();

        OutputView.printFirstDrawStatus(dealerCardDto, playerHandStatusDtos);
        return blackjackGame;
    }

    private void playGame(final BlackjackGame blackjackGame) {
        startPlayersTurn(blackjackGame);
        startDealerTurn(blackjackGame);
        printParticipantStatusesAfterDrawing(blackjackGame);
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

    private void printGameResults(final BlackjackGame blackjackGame, final BettingMoneyStore bettingMoneyStore) {
        BlackjackGameResults gameResults = BlackjackGameResults.of(blackjackGame);
        BettingResults bettingResults = BettingResults.of(bettingMoneyStore, gameResults);
        OutputView.printBettingResults(bettingResults);
    }
}
