package controller;

import domain.Game;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import domain.participant.Players;
import dto.CardDto;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import service.BlackjackService;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    public void start() {
        Players players = retryOnException(this::makePlayers);
        Deck deck = blackjackService.makeDeck();
        Dealer dealer = new Dealer();
        Game game = blackjackService.makeGame(players, dealer);

        blackjackService.initializeGame(game, deck);
        printParticipantCards(players, dealer);

        playTurn(game, players, deck);
        printResult(game, dealer, players);
    }

    private Players makePlayers() {
        String input = InputView.askPlayerNames();
        return blackjackService.makePlayers(input);
    }

    private void printParticipantCards(Players players, Dealer dealer) {
        OutputView.printParticipantCards(CardDto.fromCards(dealer.getCards()),
                blackjackService.makePlayerCardDtos(players));
    }

    private void playTurn(Game game, Players players, Deck deck) {
        for (String name : players.getAllPlayerNames()) {
            playPlayerTurn(game, players, name, deck);
        }
        playDealerTurn(game, deck);
    }

    private void playPlayerTurn(Game game, Players players, String name, Deck deck) {
        boolean shouldContinue = true;
        while (shouldContinue && !game.isPlayerBust(name)) {
            shouldContinue = retryOnException(() -> isPlayerWantHit(name));
            blackjackService.playPlayerTurn(game, name, deck, shouldContinue);
            OutputView.printPlayerCards(name, CardDto.fromCards(players.getPlayerCards(name)));
        }
    }

    private boolean isPlayerWantHit(String name) {
        String input = retryOnException(() -> InputView.askPlayerHit(name));
        return InputParser.parseHitAnswer(input);
    }

    public void playDealerTurn(Game game, Deck deck) {
        while (!game.isDealerBust()) {
            blackjackService.playDealerTurn(game, deck);
            OutputView.printDealerHit();
        }
    }

    private void printResult(Game game, Dealer dealer, Players players) {
        OutputView.printDealerCardsWithScore(CardDto.fromCards(dealer.getCards()), game.getDealerScore());

        Map<String, Result> playerResults = blackjackService.makePlayerResults(players, game);
        for (Map.Entry<String, List<CardDto>> entry : blackjackService.makePlayerCardDtos(players).entrySet()) {
            OutputView.printPlayerCardsWithScore(entry.getKey(), entry.getValue(), game.getPlayerScore(entry.getKey()));
        }

        OutputView.printGameResult(game.getDealerResult(), playerResults);
    }

    private <T> T retryOnException(Supplier<T> operation) {
        while (true) {
            try {
                return operation.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
