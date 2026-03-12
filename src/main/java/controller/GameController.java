package controller;

import java.util.List;

import domain.Amount;
import domain.Card;
import domain.Dealer;
import domain.Player;
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
        List<Player> players = setUpUsers();
        processBet(players);
        initDeal(players);
        processBlackjack(players);
        processUserTurns(players);
        processDealerTurn();
        showCardResult(players);
        showGameRecord(players);
    }

    private List<Player> setUpUsers(){
        String input = inputView.readUsers();
        return InputParser.parseUsers(input);
    }

    private void processBet(List<Player> players) {
        for(Player player : players) {
            String input = inputView.readBetAmount(player);
            Amount amount = new Amount(InputParser.parseAmount(input));
            player.bet(amount);
        }
    }

    private void initDeal(List<Player> players){
        gameService.initDeal(players, dealer);
        outputView.printInitialDeal(players, dealer);
    }

    private void processBlackjack(List<Player> players) {
        for(Player player : players) {
            boolean userIsBlackjack = player.isBlackjack();
            boolean dealerIsBlackjack = dealer.isBlackjack();
            if(userIsBlackjack && !dealerIsBlackjack) {
                outputView.printBlackjackWin(player);
                continue;
            }
            if(userIsBlackjack) {
                outputView.printBlackjackDraw(player);
            }
        }
    }

    private void processUserTurns(List<Player> players) {
        for (Player player : players) {
            if(player.isBlackjack()) {
                continue;
            }
            boolean hitAtLeastOnce = false;
            while (inputView.readWillHit(player.getName())) {
                hitAtLeastOnce = true;
                player.receiveCard(gameService.deal());
                outputView.printHand(player);
            }
            if (!hitAtLeastOnce) {
                outputView.printHand(player);
            }
        }
    }

    private void processDealerTurn() {
        int sum = dealer.getHand().stream().mapToInt(Card::getValue).sum();
        if(dealer.isBlackjack()) {
            outputView.printDealerBlackjack();
            return;
        }
        if(dealer.isHit(sum)) {
            dealer.receiveCard(gameService.deal());
            outputView.printDealerHit();
            return;
        }
        outputView.printDealerStand();
    }

    private void showCardResult(List<Player> players) {
        outputView.printCardResult(players, dealer);
    }

    private void showGameRecord(List<Player> players){
        gameService.determineResult(players, dealer);
        outputView.printGameRecord(players, dealer);
    }
}
