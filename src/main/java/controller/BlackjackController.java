package controller;

import domain.Game;
import domain.bet.BetProfit;
import domain.card.Card;
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
        Game game = initGame();
        List<Name> playersName = game.getAllPlayersName();
        askPlayerBet(game, playersName);
        game.initializeGame();
        outputView.printPlayers(game.getDealerCard(), getPlayerCards(game, playersName));

        playTurn(game, playersName);
        printResult(game, playersName);
        printProfit(game);
    }

    private Game initGame() {
        CardGenerator cardGenerator = new ShuffledCardGenerator();
        return new Game(parsePlayerNames(), new Dealer(), cardGenerator.generate());
    }

    private List<String> parsePlayerNames() {
        String input = inputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private void askPlayerBet(Game game, List<Name> playersName) {
        for (Name name : playersName) {
            String input = inputView.askPlayerBet(name);
            int playerMoney = InputParser.parseMoney(input);
            game.bettingMoney(name, playerMoney);
        }
    }

    private Map<Name, List<Card>> getPlayerCards(Game game, List<Name> playersName) {
        Map<Name, List<Card>> playerCards = new LinkedHashMap<>();
        for (Name name : playersName) {
            playerCards.put(name, game.getPlayerCard(name));
        }
        return playerCards;
    }

    private void playTurn(Game game, List<Name> playersName) {
        for (Name name : playersName) {
            playPlayerTurn(game, name);
        }
        playDealerTurn(game);
    }

    private void playPlayerTurn(Game game, Name name) {
        boolean shouldContinue = true;
        boolean isBust = false;
        while (shouldContinue && !isBust) {
            shouldContinue = isPlayerWantHit(name);
            isBust = game.playPlayerTurn(name, shouldContinue);
            outputView.printPlayerCard(name, game.getPlayerCard(name));
        }
    }

    private boolean isPlayerWantHit(Name name) {
        String input = inputView.askPlayerHit(name);
        return InputParser.parseHitAnswer(input);
    }

    private void playDealerTurn(Game game) {
        while (game.playDealerTurn()) {
            outputView.printDealerHit();
        }
    }

    private void printResult(Game game, List<Name> playerNames) {
        outputView.printDealerCardWithScore(game.getDealerCard(), game.getDealerScore());

        for (Name name : playerNames) {
            outputView.printPlayerCardWithScore(name, game.getPlayerCard(name), game.getPlayerScore(name));
        }

        Map<Name, GameResult> allPlayersResult = game.getAllPlayersResult();
        outputView.printGameResult(game.getDealerResult(), allPlayersResult);
    }

    private void printProfit(Game game) {
        BetProfit betProfit = game.calculateProfit();
        outputView.printProfit(betProfit.getDealerBetProfit(), betProfit.getPlayerBetProfit());
    }
}
