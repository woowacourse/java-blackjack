package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.BettingZone;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = generateBlackjackGame();
        drawInitialCards(blackjackGame);
        drawAdditionalCards(blackjackGame);
        printPlayerCardAndScores(blackjackGame);
        printPlayerProfit(blackjackGame);
    }

    private BlackjackGame generateBlackjackGame() {
        final Players players = generateValidPlayers();
        final BettingZone bettingZone = generateBettingZone(players);
        return new BlackjackGame(players, bettingZone);
    }

    private Players generateValidPlayers() {
        return repeatUntilGetValidInput(() -> {
            final List<String> playerNames = new ArrayList<>(inputView.readPlayers());
            return Players.from(playerNames);
        });
    }

    private <T> T repeatUntilGetValidInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatUntilGetValidInput(supplier);
        }
    }

    private BettingZone generateBettingZone(final Players players) {
        final List<Player> allPlayers = players.getGamblers();
        final LinkedHashMap<Player, Money> betMoneyByPlayers = allPlayers.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        this::generateBettingMoney,
                        (betMoney, betMoney2) -> betMoney,
                        LinkedHashMap::new
                ));
        return new BettingZone(betMoneyByPlayers);
    }

    private Money generateBettingMoney(final Player player) {
        return repeatUntilGetValidInput(() -> {
            final int amount = inputView.readBetMoney(player);
            return Money.createMoneyForBetting(amount);
        });
    }

    private void drawInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.drawInitialCards(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void drawAdditionalCards(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getGamblers();
        for (Player player : players) {
            drawByGambler(blackjackGame, player);
        }
        blackjackGame.drawByDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
    }

    private void drawByGambler(final BlackjackGame blackjackGame, final Player player) {
        while (player.isDrawable()) {
            final BlackjackCommand command = generateValidCommand(player);
            blackjackGame.drawByGambler(player, ShuffledDeck.getInstance(), command);
            outputView.printDrawResult(player);
        }
    }

    private BlackjackCommand generateValidCommand(final Player player) {
        return repeatUntilGetValidInput(() -> {
            final String input = inputView.readCommand(player);
            return BlackjackCommand.from(input);
        });
    }

    private void printPlayerCardAndScores(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getPlayers();
        for (final Player player : players) {
            outputView.printPlayerCardAndScores(player);
        }
    }

    private void printPlayerProfit(final BlackjackGame blackjackGame) {
        final Map<Player, Money> profitByPlayers = blackjackGame.calculateBettingProfit();
        outputView.printPlayerProfits(profitByPlayers);
    }
}
