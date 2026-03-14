package controller;

import java.util.List;

import domain.Amount;
import domain.Dealer;
import domain.Player;
import domain.Players;
import service.GameService;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class GameController {

    private final GameService gameService;
    private final InputView inputView;
    private final OutputView outputView;

    private final Dealer dealer = new Dealer();

    public GameController(InputView inputView, OutputView outputVIew, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputVIew;
        this.gameService = gameService;
    }

    public void run() {
        Players players = setUp();
        play(players);
        showResult(players);
    }

    private Players setUp() {
        Players players = setUpUsers();
        initDeal(players);
        return players;
    }

    private void play(Players players) {
        processBlackjack(players);
        processPlayerTurns(players);
        processDealerTurn();
    }

    private void showResult(Players players) {
        showCardResult(players);
        showGameRecord(players);
    }

    private Players setUpUsers(){
        List<String> userNames = readUserNames();
        List<Player> players = userNames.stream()
                .map(name -> new Player(name, readUserAmounts(name)))
                .toList();
        return new Players(players);
    }

    private List<String> readUserNames() {
        String input = inputView.readUserNames();
        return InputParser.parseUserNames(input);
    }

    private Amount readUserAmounts(String name) {
        String input = inputView.readBetAmount(name);
        return new Amount(InputParser.parseAmount(input));
    }

    private void initDeal(Players players){
        gameService.initDeal(players, dealer);
        outputView.printInitialDeal(players, dealer);
    }

    private void processBlackjack(Players players) {
        outputView.printBlackjackWin(players);
    }

    private void processPlayerTurns(Players players) {
        for(Player player : players.getPlayers()) {
            processPlayerTurn(player);
        }
    }

    private void processPlayerTurn(Player player) {
        while (!player.isBlackjack() && inputView.readWillHit(player.getName())) {
            gameService.deal(player);
            outputView.printHand(player);
        }
        outputView.printHand(player);
    }

    private void processDealerTurn() {
        while (dealer.isHit()) {
            gameService.deal(dealer);
            outputView.printDealerHit();
        }
    }

    private void showCardResult(Players players) {
        outputView.printCardResult(players, dealer);
    }

    private void showGameRecord(Players players){
        gameService.determineResult(players, dealer);
        outputView.printGameRecord(players);
    }
}
