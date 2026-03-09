package blackjack.controller;

import static blackjack.model.Constant.DEALER_ADD_CARD_STAND;
import static blackjack.model.Constant.TWENTY_ONE;
import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.CardCalculator;
import blackjack.model.Dealer;
import blackjack.model.Deck;
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

    private static final String Y_N_REGREX = "^[yYnN]$";

    private final Deck deck;
    private final CardCalculator cardCalculator;
    private final GameResultCalculator gameResultCalculator;

    public BlackjackController(Deck deck, CardCalculator cardCalculator,
                               GameResultCalculator gameResultCalculator) {
        this.deck = deck;
        this.cardCalculator = cardCalculator;
        this.gameResultCalculator = gameResultCalculator;
    }

    public void run() {
        List<Player> players = retryUntilSuccess(() -> {
            String input = InputView.readPlayerName();
            return PlayerParser.parse(input);
        });
        Dealer dealer = new Dealer();

        provideInitCardsPrint(players, dealer);

        hit(players, dealer);

        List<User> users = new ArrayList<>(players);
        users.addFirst(dealer);

        List<GameSummary> gameSummaries = createGameSummaryAndPrint(users);

        createGameResultAndPrint(gameSummaries, users);

        InputView.closeScanner();
    }

    private void provideInitCardsPrint(List<Player> players, Dealer dealer) {
        deck.provideInitCards(players, dealer);
        OutputView.printInitCards(players, dealer);
    }

    private void createGameResultAndPrint(List<GameSummary> gameSummaries, List<User> users) {
        gameResultCalculator.calculate(gameSummaries);
        OutputView.printGameResult(users);
    }

    private List<GameSummary> createGameSummaryAndPrint(List<User> users) {
        List<GameSummary> gameSummaries = new ArrayList<>();
        for (User user : users) {
            int totalScore = cardCalculator.totalScore(user.cards());
            boolean bust = cardCalculator.calculateBust(totalScore);
            boolean blackjack = cardCalculator.calculateBlackjack(user.cards());

            GameSummary gameSummary = new GameSummary(user, totalScore, bust, blackjack);
            gameSummaries.add(gameSummary);
            OutputView.printCardStatus(gameSummary);
        }
        return gameSummaries;
    }

    public void hit(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            while (retryUntilSuccess(() -> checkY(player)) && checkAddCard(player)) {
                deck.provideOneCard(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (cardCalculator.totalScore(dealer.cards()) < DEALER_ADD_CARD_STAND) {
            OutputView.printDealerHit();
            deck.provideOneCard(dealer);
        }
    }

    private static boolean checkY(Player player) {
        String input = InputView.readCardAdd(player).trim();
        Validator.validateEmpty(input, ErrorMessage.ERROR_EMPTY_INPUT.getErrorMessage());
        Validator.validateRegrex(Y_N_REGREX, input, ErrorMessage.ERROR_NOT_Y_N_INPUT.getErrorMessage());
        return input.equals("y");
    }

    boolean checkAddCard(Player player) {
        if (cardCalculator.totalScore(player.cards()) >= TWENTY_ONE) {
            OutputView.printCantAddCard();
            return false;
        }
        return true;
    }
}

