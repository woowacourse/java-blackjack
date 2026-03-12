package controller;

import domain.BetAmount;
import domain.Game;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.enums.Result;
import dto.CardDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import service.BlackjackService;
import view.InputParser;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    public void start() {
        List<String> names = retryOnException(this::askPlayerNames);
        Deck deck = new Deck(CardGenerator.generateCards());
        Game game = new Game(names);

        playGame(game, deck);
    }

    private List<String> askPlayerNames() {
        String input = InputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private void playGame(Game game, Deck deck) {
        game.initializeGame(deck);
        Map<String, BetAmount> betAmounts = retryOnException(() -> makeBetAmounts(game));
        printParticipantCards(game);
        playTurn(game, deck);
        printResult(game, betAmounts);
    }

    private Map<String, BetAmount> makeBetAmounts(Game game) {
        Map<String, BetAmount> betAmounts = new LinkedHashMap<>();
        for (String name : game.getAllPlayerNames()) {
            String input = InputView.askBetAmount(name);
            int amount = InputParser.parseBetAmount(input);
            betAmounts.put(name, new BetAmount(amount));
        }
        return betAmounts;
    }

    private void printParticipantCards(Game game) {
        OutputView.printParticipantCards(CardDto.fromCards(game.getDealerCards()),
                blackjackService.makePlayerCardDtos(game));
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

    private void printResult(Game game, Map<String, BetAmount> betAmounts) {
        OutputView.printDealerCardsWithScore(CardDto.fromCards(game.getDealerCards()), game.getDealerScore());
        printFinalCards(game);
        printProfits(game, betAmounts);
    }

    private void printFinalCards(Game game) {
        for (Map.Entry<String, List<CardDto>> entry : blackjackService.makePlayerCardDtos(game).entrySet()) {
            OutputView.printPlayerCardsWithScore(entry.getKey(), entry.getValue(), game.getPlayerScore(entry.getKey()));
        }
    }

    private void printProfits(Game game, Map<String, BetAmount> betAmounts) {
        Map<String, Result> playerResults = blackjackService.makePlayerResults(game);
        Map<String, Integer> playerProfits = blackjackService.calculateAllPlayerProfits(playerResults, betAmounts);
        int dealerProfit = blackjackService.calculateDealerProfit(playerProfits);
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
