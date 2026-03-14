package controller;

import domain.Game;
import domain.card.Deck;
import domain.card.ShuffledCardGenerator;
import domain.enums.Result;
import domain.participant.BetAmounts;
import dto.CardDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import view.InputParser;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    public void start() {
        Deck deck = new Deck(new ShuffledCardGenerator());
        Game game = retryOnException(() -> makeGame(deck));

        playGame(game, deck);
    }

    private Game makeGame(Deck deck) {
        List<String> names = askPlayerNames();
        return new Game(names, deck);
    }

    private List<String> askPlayerNames() {
        String input = InputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private void playGame(Game game, Deck deck) {
        BetAmounts betAmounts = makeBetAmounts(game);
        printInitCards(game);
        playTurn(game, deck);
        printResult(game, betAmounts);
    }

    private BetAmounts makeBetAmounts(Game game) {
        BetAmounts betAmounts = new BetAmounts(game.getAllPlayerNames());
        for (String name : game.getAllPlayerNames()) {
            int amount = retryOnException(() -> askBetAmount(name));
            betAmounts.addBetAmount(name, amount);
        }
        return betAmounts;
    }

    private int askBetAmount(String name) {
        String input = InputView.askBetAmount(name);
        return InputParser.parseBetAmount(input);
    }

    private void printInitCards(Game game) {
        List<String> names = game.getAllPlayerNames();
        OutputView.printDistributeComplete(names);
        OutputView.printDealerFirstCard(CardDto.fromCards(game.getDealerCards()));
        for (String name : names) {
            OutputView.printPlayerCards(name, CardDto.fromCards(game.getPlayerCards(name)));
        }
        OutputView.printLineSeparator();
    }

    private void playTurn(Game game, Deck deck) {
        for (String name : game.getAllPlayerNames()) {
            playPlayerTurn(game, name, deck);
        }
        playDealerTurn(game, deck);
    }

    private void playPlayerTurn(Game game, String name, Deck deck) {
        boolean wantHit = true;
        while (!game.isPlayerEnd(name, wantHit)) {
            wantHit = retryOnException(() -> isPlayerWantHit(name));
            game.playerHit(name, deck, game.isPlayerEnd(name, wantHit));
            OutputView.printPlayerCards(name, CardDto.fromCards(game.getPlayerCards(name)));
        }
    }

    private boolean isPlayerWantHit(String name) {
        String input = retryOnException(() -> InputView.askPlayerHit(name));
        return InputParser.parseHitAnswer(input);
    }

    public void playDealerTurn(Game game, Deck deck) {
        while (!game.isDealerEnd()) {
            game.dealerHit(deck);
            OutputView.printDealerHit();
        }
    }

    private void printResult(Game game, BetAmounts betAmounts) {
        printFinalCards(game);
        printProfits(game, betAmounts);
    }

    private void printFinalCards(Game game) {
        OutputView.printDealerCardsWithScore(CardDto.fromCards(game.getDealerCards()), game.calculateDealerScore());
        for (String name : game.getAllPlayerNames()) {
            OutputView.printPlayerCardsWithScore(name, CardDto.fromCards(game.getPlayerCards(name)),
                    game.calculatePlayerScore(name));
        }
    }

    private void printProfits(Game game, BetAmounts betAmounts) {
        Map<String, Result> playerResults = game.calculateAllPlayerResults();
        Map<String, Integer> playerProfits = betAmounts.calculatePlayerProfits(playerResults);
        int dealerProfit = betAmounts.calculateDealerProfit(playerProfits);
        OutputView.printGameResult(dealerProfit, playerProfits);
    }

    private <T> T retryOnException(Supplier<T> operation) {
        Optional<T> result = tryOperation(operation);
        while (result.isEmpty()) {
            result = tryOperation(operation);
        }
        return result.orElseThrow();
    }

    private <T> Optional<T> tryOperation(Supplier<T> operation) {
        try {
            return Optional.of(operation.get());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return Optional.empty();
        }
    }
}
