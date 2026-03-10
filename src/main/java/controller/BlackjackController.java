package controller;

import domain.Game;
import domain.card.Card;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import service.CardGenerator;
import service.ShuffledCardGenerator;
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
        CardGenerator cardGenerator = new ShuffledCardGenerator();
        Deck deck = new Deck(cardGenerator.generate());
        Game game = new Game(makePlayers(), new Dealer());
        List<String> playersName = game.getAllPlayersName();

        game.initializeGame(deck);
        outputView.printPlayers(game.getDealerCard(), getPlayerCards(game, playersName));

        playTurn(game, playersName, deck);
        printResult(game, playersName);
    }

    private List<String> makePlayers() {
        String input = inputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private Map<String, List<Card>> getPlayerCards(Game game, List<String> playersName) {
        Map<String, List<Card>> playerCards = new LinkedHashMap<>();
        for (String name : playersName) {
            playerCards.put(name, game.getPlayerCard(name));
        }
        return playerCards;
    }

    private void playTurn(Game game, List<String> playersName, Deck deck) {
        for (String name : playersName) {
            playPlayerTurn(game, name, deck);
        }
        playDealerTurn(game, deck);
    }

    private void playPlayerTurn(Game game, String name, Deck deck) {
        boolean shouldContinue = true;
        boolean isBust = false;
        while (shouldContinue && !isBust) {
            shouldContinue = isPlayerWantHit(name);
            isBust = game.playPlayerTurn(name, deck, shouldContinue);
            outputView.printPlayerCard(name, game.getPlayerCard(name));
        }
    }

    private boolean isPlayerWantHit(String name) {
        String input = inputView.askPlayerHit(name);
        return InputParser.parseHitAnswer(input);
    }

    private void playDealerTurn(Game game, Deck deck) {
        boolean isBust = false;
        while (!isBust) {
            isBust = game.playDealerTurn(deck);
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
