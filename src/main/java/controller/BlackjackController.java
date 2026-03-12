package controller;

import domain.Game;
import domain.bet.Bet;
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
        Bet bet = new Bet(playersName);

        game.initializeGame(deck);
        askPlayerBet(bet, playersName);
        outputView.printPlayers(game.getDealerCard(), getPlayerCards(game, playersName));

        playTurn(game, playersName, deck);
        printResult(game, bet, playersName);
    }

    private List<String> makePlayers() {
        String input = inputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private void askPlayerBet(Bet bet, List<Name> playersName) {
        for (Name name : playersName) {
            String input = inputView.askPlayerBet(name);
            int playerMoney = InputParser.parseMoney(input);
            bet.bettingMoney(name, playerMoney);
        }
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
        while (game.playDealerTurn(deck)) {
            outputView.printDealerHit();
        }
    }

    private void printResult(Game game, Bet bet, List<Name> playerNames) {
        outputView.printDealerCardWithScore(game.getDealerCard(), game.getDealerScore());

        Map<Name, GameResult> playerResults = new LinkedHashMap<>();
        for (Name name : playerNames) {
            outputView.printPlayerCardWithScore(name, game.getPlayerCard(name), game.getPlayerScore(name));
            playerResults.put(name, game.getPlayerResult(name));
        }

        outputView.printGameResult(game.getDealerResult(), playerResults);

        printProfit(bet, playerResults);
    }

    private void printProfit(Bet bet, Map<Name, GameResult> playerResults) {
        bet.calculateProfit(playerResults);
        outputView.printProfit(bet.getDealerBetProfit(), bet.getPlayerBetProfit());
    }
}
