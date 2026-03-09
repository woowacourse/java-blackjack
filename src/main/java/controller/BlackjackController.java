package controller;

import domain.Game;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Players players = makePlayers();
        Deck deck = new Deck(CardGenerator.generateCards());
        Game game = new Game(players, new Dealer());

        game.initializeGame(deck);
        outputView.printPlayers(game.getDealerCard(), game.getAllPlayerCard());

        List<String> playersName = players.getAllPlayersName();
        playTurn(game, playersName, deck);
        printResult(game, playersName);
    }

    private Players makePlayers() {
        String input = inputView.askPlayerNames();
        List<String> names = InputParser.parseNames(input);
        return new Players(names);
    }

    private void playTurn(Game game, List<String> playersName, Deck deck) {
        for (String name : playersName) {
            playPlayerTurn(game, name, deck);
        }
        playDealerTurn(game, deck);
    }


    private void playPlayerTurn(Game game, String name, Deck deck) {
        boolean shouldContinue = true;
        while (shouldContinue && !game.isPlayerBust(name)) {
            shouldContinue = isPlayerWantHit(name);
            game.playerHit(name, deck, shouldContinue);
            outputView.printPlayerCard(name, game.getPlayerCard(name));
        }
    }

    private boolean isPlayerWantHit(String name) {
        String input = inputView.askPlayerHit(name);
        return InputParser.parseHitAnswer(input);
    }

    private void playDealerTurn(Game game, Deck deck) {
        while (!game.isDealerBust()) {
            game.dealerHit(deck);
            outputView.printDealerHit();
        }
    }

    private void printResult(Game game, List<String> playerNames) {
        outputView.printDealerCardWithScore(game.getDealerCard(), game.getDealerScore());

        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (String name : playerNames) {
            outputView.printPlayerCardWithScore(name, game.getPlayerCard(name), game.getPlayerScore(name));
            playerResults.put(name, game.getPlayerResult(name));
        }

        outputView.printGameResult(game.getDealerResult(), playerResults);
    }
}
