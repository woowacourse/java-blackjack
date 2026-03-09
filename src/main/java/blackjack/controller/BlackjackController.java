package blackjack.controller;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.ErrorMessage;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.util.PlayerParser;
import blackjack.util.Validator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;


public class BlackjackController {

    private static final String Y_N_REGREX = "^[yYnN]$";

    private final Deck deck;

    public BlackjackController(Deck deck) {
        this.deck = deck;
    }

    public void run() {
        List<Player> playerList = retryUntilSuccess(() -> {
            String input = InputView.readPlayerName();
            return PlayerParser.parse(input);
        });

        Players players = new Players(playerList);
        Dealer dealer = new Dealer();

        deck.provideInitCards(players, dealer);
        OutputView.printInitCards(players.all(), dealer);

        hit(players, dealer);

        List<GameSummary> gameSummaries = players.calculateGameResult(dealer);

        for (GameSummary gameSummary : gameSummaries) {
            OutputView.printCardStatus(gameSummary);
        }

        OutputView.printGameResult(players, dealer);

        InputView.closeScanner();
    }

    public void hit(Players players, Dealer dealer) {
        for (Player player : players.all()) {
            while (player.canHit() && retryUntilSuccess(() -> checkY(player))) {
                deck.provideOneCard(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (dealer.canHit()) {
            OutputView.printDealerHit();
            deck.provideOneCard(dealer);
        }
    }

    private boolean checkY(Player player) {
        String input = InputView.readCardAdd(player).trim();
        Validator.validateEmpty(input, ErrorMessage.ERROR_EMPTY_INPUT.getErrorMessage());
        Validator.validateRegrex(Y_N_REGREX, input, ErrorMessage.ERROR_NOT_Y_N_INPUT.getErrorMessage());
        return input.equals("y");
    }

}

