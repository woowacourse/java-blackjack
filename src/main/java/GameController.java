import domain.Dealer;
import domain.DealerProfit;
import domain.User;
import domain.UserProfit;
import view.InputParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    private final Dealer dealer;

    public GameController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
        this.dealer = new Dealer();
    }

    public void run() {
        List<User> users = setUpUsers();
        initDeal(users);
        processUserTurns(users);
        processDealerTurn();
        showCardResult(users);
        showGameRecord(users);
        processTotalProfit(users);
    }

    private List<User> setUpUsers(){
        String input = inputView.readUsers();
        List<String> names = InputParser.parseToList(input);
        List<Integer> betAmounts = inputView.readBetAmounts(names);
        return InputParser.parseToUsers(names, betAmounts);
    }

    private void initDeal(List<User> users){
        gameService.initDeal(users, dealer);
        outputView.printInitialDeal(users, dealer);
    }

    private void processUserTurns(List<User> users) {
        users.forEach(this::processUserTurn);
    }

    private void processUserTurn(User user) {
        boolean hit = false;
        while (inputView.readWillHit(user.getName())) {
            hit = true;
            user.receiveCard(gameService.deal());
            outputView.printHand(user);
        }
        if (!hit) {
            outputView.printHand(user);
        }
    }

    private void processDealerTurn() {
        while (dealer.isHit()) {
            dealer.receiveCard(gameService.deal());
            outputView.printDealerHit();
            dealer.calculateScore();
        }
        outputView.printDealerStand();
    }

    private void showCardResult(List<User> users) {
        outputView.printCardResult(users, dealer);
    }

    private void showGameRecord(List<User> users){
        gameService.settleResult(users, dealer);
        outputView.printGameRecord(users, dealer);
    }

    private void processTotalProfit(List<User> users) {
        List<UserProfit> userProfits = gameService.createUsersProfit(users);
        DealerProfit dealerProfit = gameService.upsertDealerProfit(userProfits);
        outputView.printTotalProfit(userProfits, dealerProfit);
    }
}
