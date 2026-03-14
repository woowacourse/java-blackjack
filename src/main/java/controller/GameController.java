package controller;

import java.util.List;

import domain.Amount;
import domain.Player;
import domain.Players;
import domain.BlackjackGame;
import view.InputView;
import view.OutputView;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private BlackjackGame blackjackGame;

    public GameController(InputView inputView, OutputView outputVIew) {
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        setUp();
        play();
        showResult();
    }

    private void setUp() {
        Players players = createUsers();
        this.blackjackGame = new BlackjackGame(players);
        initDeal();
    }

    private void play() {
        processBlackjack();
        processPlayerTurns();
        processDealerTurn();
    }

    private void showResult() {
        showCardResult();
        showGameRecord();
    }

    private Players createUsers(){
        List<String> userNames = readUserNames();
        List<Player> players = userNames.stream()
                .map(name -> new Player(name, readUserAmounts(name)))
                .toList();
        return new Players(players);
    }

    private List<String> readUserNames() {
        return inputView.readUserNames();
    }

    private Amount readUserAmounts(String name) {
        return new Amount(inputView.readBetAmount(name));
    }

    private void initDeal(){
        blackjackGame.initDeal();
        outputView.printInitialDeal(blackjackGame.getPlayers(), blackjackGame.getDealer());
    }

    private void processBlackjack() {
        List<Player> blackjackPlayers = blackjackGame.getBlackjackPlayers();
        outputView.printBlackjacks(blackjackPlayers);
    }

    private void processPlayerTurns() {
        for(Player player : blackjackGame.getPlayers()) {
            processPlayerTurn(player);
        }
    }

    private void processPlayerTurn(Player player) {
        while (!player.isBlackjack() && inputView.readWillHit(player.getName())) {
            blackjackGame.deal(player);
            outputView.printHand(player);
        }
        outputView.printHand(player);
    }

    private void processDealerTurn() {
        while (blackjackGame.getDealer().isHit()) {
            blackjackGame.deal(blackjackGame.getDealer());
            outputView.printDealerHit();
        }
    }

    private void showCardResult() {
        outputView.printCardResult(blackjackGame.getPlayers(), blackjackGame.getDealer());
    }

    private void showGameRecord(){
        blackjackGame.determineResult();
        outputView.printGameRecord(blackjackGame.getPlayers());
    }
}
