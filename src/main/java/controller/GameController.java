package controller;

import java.util.List;

import domain.Card;
import domain.Dealer;
import domain.User;
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
        List<User> users = setUpUsers();
        initDeal(users);
        processUserTurns(users);
        processDealerTurn();
        showCardResult(users);
        showGameRecord(users);
    }

    private List<User> setUpUsers(){
        String input = inputView.readUsers();
        return InputParser.parseUsers(input);
    }

    private void initDeal(List<User> users){
        gameService.initDeal(users, dealer);
        outputView.printInitialDeal(users, dealer);
    }

    private void processUserTurns(List<User> users) {
        for (User user : users) {
            boolean hitAtLeastOnce = false;
            while (inputView.readWillHit(user.getName())) {
                hitAtLeastOnce = true;
                user.receiveCard(gameService.deal());
                outputView.printHand(user);
            }
            if (!hitAtLeastOnce) {
                outputView.printHand(user);
            }
        }
    }

    private void processDealerTurn() {
        int sum = dealer.getHand().stream().mapToInt(Card::getValue).sum();
        if(dealer.isHit(sum)) {
            dealer.receiveCard(gameService.deal());
            outputView.printDealerHit();
            return;
        }
        outputView.printDealerStand();
    }

    private void showCardResult(List<User> users) {
        outputView.printCardResult(users, dealer);
    }

    private void showGameRecord(List<User> users){
        gameService.determineResult(users, dealer);
        outputView.printGameRecord(users, dealer);
    }
}
