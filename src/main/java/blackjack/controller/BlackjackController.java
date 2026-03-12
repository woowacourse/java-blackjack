package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.GameResult;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.view.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Deck deck;

    public BlackjackController(InputView inputView, OutputView outputView, Deck deck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck = deck;
    }

    public void run() {
        Players players = setupPlayers();
        Dealer dealer = new Dealer();

        initCards(players, dealer);
        hit(players, dealer);

        displayGameSummary(players, dealer);
        displayGameResult(players, dealer);

        inputView.closeScanner();
    }

    private Players setupPlayers() {
        List<String> playerNames = retry(() -> {
            String input = inputView.readPlayerName();
            return InputParser.parse(input);

        });

        List<Player> allPlayers = new ArrayList<>();

        for (String playerName : playerNames) {
            int betAmount = retry(() -> inputView.readBetAmount(playerName));
            allPlayers.add(new Player(playerName, betAmount));
        }

        return new Players(allPlayers);
    }

    private void initCards(Players players, Dealer dealer) {
        deck.provideInitCards(players, dealer);
        outputView.printInitCards(players.all(), dealer);

    }

    private void hit(Players players, Dealer dealer) {
        for (Player player : players.all()) {
            while (player.canHit() && retry(() -> inputView.readCardAdd(player))) {
                deck.provideOneCard(player);
                outputView.printPlayerCards(player);
            }
        }

        while (dealer.canHit()) {
            outputView.printDealerHit();
            deck.provideOneCard(dealer);
        }
    }

    private void displayGameSummary(Players players, Dealer dealer) {
        List<GameSummary> gameSummaries = players.calculateGameSummary(dealer);
        outputView.printGameSummary(gameSummaries);
    }

    private void displayGameResult(Players players, Dealer dealer) {
        List<GameResult> gameResults = players.calculateGameResult(dealer);
        outputView.printGameResult(gameResults);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}

