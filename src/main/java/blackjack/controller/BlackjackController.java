package blackjack.controller;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.util.PlayerParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;


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
        List<Player> playerList = retryUntilSuccess(() -> {
            String input = inputView.readPlayerName();
            return PlayerParser.parse(input);
        });

        Players players = new Players(playerList);
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
            while (player.canHit() && retryUntilSuccess(() -> inputView.readCardAdd(player))) {
                deck.provideOneCard(player);
                outputView.printPlayerCards(player);
            }
        }

        while (dealer.canHit()) {
            outputView.printDealerHit();
            deck.provideOneCard(dealer);
        }
    }

}

