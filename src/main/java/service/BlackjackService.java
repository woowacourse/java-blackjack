package service;

import domain.Game;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.Dealer;
import domain.participant.Players;
import dto.CardDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import util.InputParser;
import view.OutputView;

public class BlackjackService {
    public Players makePlayers(String input) {
        return new Players(InputParser.parseNames(input));
    }

    public Deck makeDeck() {
        return new Deck(CardGenerator.generateCards());
    }

    public Game makeGame(Players players, Dealer dealer) {
        return new Game(players, dealer);
    }

    public void initializeGame(Game game, Deck deck) {
        game.initializeGame(deck);
    }

    public void playPlayerTurn(Game game, String name, Deck deck, boolean shouldContinue) {
        game.playerHit(name, deck, shouldContinue);
    }

    public void playDealerTurn(Game game, Deck deck) {
        while (!game.isDealerBust()) {
            game.dealerHit(deck);
            OutputView.printDealerHit();
        }
    }

    public Map<String, List<CardDto>> makePlayerCardDtos(Players players) {
        Map<String, List<CardDto>> playerCards = new LinkedHashMap<>();
        for (String name : players.getAllPlayerNames()) {
            playerCards.put(name, CardDto.fromCards(players.getPlayerCards(name)));
        }
        return playerCards;
    }

    public Map<String, Result> makePlayerResults(Players players, Game game) {
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (String name : players.getAllPlayerNames()) {
            playerResults.put(name, game.getPlayerResult(name));
        }
        return playerResults;
    }
}
