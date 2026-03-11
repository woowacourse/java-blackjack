package controller;

import domain.Game;
import domain.card.Card;
import domain.card.Deck;
import domain.enums.GameResult;
import domain.participant.Dealer;
import domain.participant.Name;
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
        List<Name> playersName = game.getAllPlayersName();

        game.initializeGame(deck);
        outputView.printPlayers(game.getDealerCard(), getPlayerCards(game, playersName));

        playTurn(game, playersName, deck);
        printResult(game, playersName);
    }

    private List<String> makePlayers() {
        String input = inputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private Map<Name, List<Card>> getPlayerCards(Game game, List<Name> playersName) {
        Map<Name, List<Card>> playerCards = new LinkedHashMap<>();
        for (Name name : playersName) {
            playerCards.put(name, game.getPlayerCard(name));
        }
        return playerCards;
    }

    private void playTurn(Game game, List<Name> playersName, Deck deck) {
        for (Name name : playersName) {
            playPlayerTurn(game, name, deck);
        }
        playDealerTurn(game, deck);
    }

    private void playPlayerTurn(Game game, Name name, Deck deck) {
        boolean shouldContinue = true;
        boolean isBust = false;
        while (shouldContinue && !isBust) {
            shouldContinue = isPlayerWantHit(name);
            isBust = game.playPlayerTurn(name, deck, shouldContinue);
            outputView.printPlayerCard(name, game.getPlayerCard(name));
        }
    }

    private boolean isPlayerWantHit(Name name) {
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

    private void printResult(Game game, List<Name> playerNames) {
        outputView.printDealerCardWithScore(game.getDealerCard(), game.getDealerScore());

        Map<Name, GameResult> playerResults = new LinkedHashMap<>();
        for (Name name : playerNames) {
            outputView.printPlayerCardWithScore(name, game.getPlayerCard(name), game.getPlayerScore(name));
            playerResults.put(name, game.getPlayerResult(name));
        }

        outputView.printGameResult(game.getDealerResult(), playerResults);
    }
}
