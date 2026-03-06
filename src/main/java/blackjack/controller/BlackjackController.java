package blackjack.controller;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.CardCalculator;
import blackjack.model.CardProvider;
import blackjack.model.Dealer;
import blackjack.model.ErrorMessage;
import blackjack.model.GameResultCalculator;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.User;
import blackjack.util.PlayerParser;
import blackjack.util.Validator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;


public class BlackjackController {

    private final CardProvider cardProvider;
    private final CardCalculator cardCalculator;
    private final GameResultCalculator gameResultCalculator;

    public BlackjackController(CardProvider cardProvider, CardCalculator cardCalculator,
                               GameResultCalculator gameResultCalculator) {
        this.cardProvider = cardProvider;
        this.cardCalculator = cardCalculator;
        this.gameResultCalculator = gameResultCalculator;
    }

    public void run() {
        List<Player> players = retryUntilSuccess(() -> {
            String input = InputView.readPlayerName();
            return PlayerParser.parse(input);
        });
        Dealer dealer = new Dealer();

        cardProvider.provideInitCards(players, dealer);
        OutputView.printInitCards(players, dealer);

        hit(players, dealer);

        List<User> users = new ArrayList<>(players);
        users.addFirst(dealer);

        List<GameSummary> gameSummaries = new ArrayList<>();
        for (User user : users) {
            int totalScore = cardCalculator.totalScore(user.getCardStatus().getCards());
            boolean bust = cardCalculator.calculateBust(totalScore);
            boolean blackjack = cardCalculator.calculateBlackjack(user.getCardStatus().getCards());

            GameSummary gameSummary = new GameSummary(user, totalScore, bust, blackjack);
            gameSummaries.add(gameSummary);
            OutputView.printCardStatus(gameSummary);
        }

        gameResultCalculator.calculate(gameSummaries);

        OutputView.printGameResult(users);
    }

    public void hit(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            while (retryUntilSuccess(() -> checkY(player)) && checkAddCard(player)) {
                cardProvider.provideOneCard(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (cardCalculator.totalScore(dealer.getCardStatus().getCards()) < 17) {
            OutputView.printDealerHit();
            cardProvider.provideOneCard(dealer);
        }
    }

    private static boolean checkY(Player player) {
        String input = InputView.readCardAdd(player).trim();
        Validator.validateEmpty(input, ErrorMessage.ERROR_EMPTY_INPUT.getErrorMessage());
        Validator.validateRegrex("^[yYnN]$", input, ErrorMessage.ERROR_NOT_Y_N_INPUT.getErrorMessage());
        return input.equals("y");
    }

    boolean checkAddCard(Player player) {
        if (cardCalculator.totalScore(player.getCardStatus().getCards()) >= 21) {
            OutputView.printCantAddCard();
            return false;
        }
        return true;
    }
}
