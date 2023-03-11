package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.BettingSystem;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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

        initialDraw(blackjackGame);
        draw(blackjackGame);
        printPlayerResult(blackjackGame);
        final Map<Player, Money> betMoneyMap = blackjackGame.calculateBet();
        outputView.printGameResult(betMoneyMap);
    }

    private BlackjackGame generateBlackjackGame() {
        final Players players = repeatUntilGetValidInput(inputView::readPlayers);
        final BettingSystem bettingSystem = generateBettingSystem(players);
        return new BlackjackGame(players, bettingSystem);
    }

    private BettingSystem generateBettingSystem(final Players players) {
        final List<Player> gamePlayers = players.getPlayers();
        final LinkedHashMap<Player, Money> betMoneyByPlayers = gamePlayers.stream()
                .filter(player -> !player.isDealer())
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> Money.createMoneyForBetting(repeatUntilGetValidInput(() -> inputView.readBetMoney(player))),
                        (betMoney, betMoney2) -> betMoney,
                        LinkedHashMap::new
                ));
        return new BettingSystem(betMoneyByPlayers);
    }

    private void initialDraw(final BlackjackGame blackjackGame) {
        blackjackGame.drawInitialCards(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getPlayers();
        for (Player player : players) {
            drawByGambler(blackjackGame, player);
        }
        blackjackGame.drawByDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
    }

    private void drawByGambler(final BlackjackGame blackjackGame, final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = repeatUntilGetValidInput(() -> inputView.readCommand(player));
            blackjackGame.drawByGambler(player, ShuffledDeck.getInstance(), command);
            outputView.printDrawResult(player);
        }
    }

    private boolean isDrawable(final Player player) {
        return player.isDrawable() && !player.isDealer();
    }

    private <T> T repeatUntilGetValidInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatUntilGetValidInput(supplier);
        }
    }

    private void printPlayerResult(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getPlayers();
        for (final Player player : players) {
            outputView.printPlayerResult(player);
        }
    }
}
