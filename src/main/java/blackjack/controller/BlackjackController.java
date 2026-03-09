package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
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
        Players players = retry(() -> {
            String input = inputView.readPlayerName();
            List<String> names = InputParser.parse(input);
            return new Players(names);
        });

        Dealer dealer = new Dealer();

        deck.provideInitCards(players, dealer);
        outputView.printInitCards(players.all(), dealer);

        hit(players, dealer);

        List<GameSummary> gameSummaries = players.calculateGameResult(dealer);

        for (GameSummary gameSummary : gameSummaries) {
            outputView.printCardStatus(gameSummary);
        }

        outputView.printGameResult(players, dealer);

        inputView.closeScanner();
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

