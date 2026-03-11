package controller;

import domain.Game;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Players;
import dto.CardDto;
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
        Players players = retryOnException(this::makePlayers);
        Deck deck = new Deck(CardGenerator.generateCards());
        Game game = new Game(players);

        playGame(game, deck);
    }

    private Players makePlayers() {
        String input = InputView.askPlayerNames();
        List<String> names = InputParser.parseNames(input);
        return new Players(names);
    }

    private void playGame(Game game, Deck deck) {
        game.initializeGame(deck);
        printParticipantCards(game);

        playTurn(game, deck);
        printResult(game);
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
        boolean isPlayerEnd = false;
        boolean wantHit = true;
        while (!isPlayerEnd && wantHit) {
            wantHit = retryOnException(() -> isPlayerWantHit(name));
            game.playerHit(name, deck, isPlayerEnd);
            isPlayerEnd = game.isPlayerEnd(name, wantHit);
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
        OutputView.printDealerCardsWithScore(CardDto.fromCards(game.getDealerCards()), game.getDealerScore());

        Map<String, Result> playerResults = blackjackService.makePlayerResults(game);
        for (Map.Entry<String, List<CardDto>> entry : blackjackService.makePlayerCardDtos(game).entrySet()) {
            OutputView.printPlayerCardsWithScore(entry.getKey(), entry.getValue(), game.getPlayerScore(entry.getKey()));
        }

        OutputView.printGameResult(game.getDealerResult(), playerResults);
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
