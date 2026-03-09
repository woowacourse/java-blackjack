package controller;

import domain.Game;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        Players players = blackjackService.makePlayers(askPlayerNames());
        Deck deck = blackjackService.makeDeck();
        Game game = blackjackService.makeGame(players);

        blackjackService.initializeGame(game, deck);
        OutputView.printPlayers(game.getDealerCard(), game.getAllPlayersCards());

        List<String> playersName = players.getAllPlayerNames();
        playTurn(game, deck);
        printResult(game, playersName);
    }

    private List<String> askPlayerNames() {
        String input = InputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private void playTurn(Game game, Deck deck) {
        for (String name : game.getAllPlayerNames()) {
            playPlayerTurn(game, name, deck);
        }
        blackjackService.playDealerTurn(game, deck);
    }

    private void playPlayerTurn(Game game, String name, Deck deck) {
        boolean shouldContinue = true;
        while (shouldContinue && !game.isPlayerBust(name)) {
            shouldContinue = isPlayerWantHit(name);
            blackjackService.playPlayerTurn(game, name, deck, shouldContinue);
            OutputView.printPlayerCard(name, game.getPlayerCards(name));
        }
    }

    private boolean isPlayerWantHit(String name) {
        String input = InputView.askPlayerHit(name);
        return InputParser.parseHitAnswer(input);
    }

    private void printResult(Game game, List<String> playerNames) {
        OutputView.printDealerCardWithScore(game.getDealerCard(), game.getDealerScore());

        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (String name : playerNames) {
            OutputView.printPlayerCardWithScore(name, game.getPlayerCards(name), game.getPlayerScore(name));
            playerResults.put(name, game.getPlayerResult(name));
        }

        OutputView.printGameResult(game.getDealerResult(), playerResults);
    }
}
