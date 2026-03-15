package controller;

import domain.Game;
import domain.card.Deck;
import domain.card.ShuffledCardGenerator;
import domain.participant.BetAmounts;
import domain.participant.Players;
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
        Game game = makeGame(deck);

        playGame(game, deck);
    }

    private Game makeGame(Deck deck) {
        Players players = retryOnException(this::makePlayers);
        BetAmounts betAmounts = retryOnException(() -> makeBetAmounts(players.getAllPlayerNames()));
        return new Game(players, betAmounts, deck);
    }

    private Players makePlayers() {
        String input = InputView.askPlayerNames();
        List<String> names = InputParser.parseNames(input);
        return new Players(names);
    }

    private void playGame(Game game, Deck deck) {
        printInitCards(game);
        playTurn(game, deck);
        printResult(game);
    }

    private BetAmounts makeBetAmounts(List<String> names) {
        BetAmounts betAmounts = new BetAmounts(names);
        for (String name : names) {
            int amount = askBetAmount(name);
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

    private void printResult(Game game) {
        printFinalCards(game);
        printProfits(game);
    }

    private void printFinalCards(Game game) {
        OutputView.printDealerCardsWithScore(CardDto.fromCards(game.getDealerCards()), game.calculateDealerScore());
        for (String name : game.getAllPlayerNames()) {
            OutputView.printPlayerCardsWithScore(name, CardDto.fromCards(game.getPlayerCards(name)),
                    game.calculatePlayerScore(name));
        }
    }

    private void printProfits(Game game) {
        Map<String, Integer> playerProfits = game.calculatePlayerProfits();
        int dealerProfit = game.calculateDealerProfit();
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
