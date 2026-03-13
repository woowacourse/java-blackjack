package blackjack.controller;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameSummary;
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

        displayGameSummaries(players, dealer);
        displayGameResults(players, dealer);

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

    private void displayGameSummaries(Players players, Dealer dealer) {
        List<GameSummary> gameSummaries = players.calculateGameSummaries(dealer);
        outputView.printGameSummary(gameSummaries);
    }

    private void displayGameResults(Players players, Dealer dealer) {
        List<GameResult> gameResults = players.calculateGameResults(dealer);
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

