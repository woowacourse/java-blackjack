import domain.player.Dealer;
import domain.result.DealerProfit;
import domain.result.RoundBetInfo;
import domain.player.User;
import domain.result.UserProfit;
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

    private static final int INITIAL_ROUND = 1;

    public void run() {
        List<RoundBetInfo> roundBetInfos = setUpRoundBetInfos();
        List<User> users = extractUsers(roundBetInfos);
        initDeal(users);
        processUserTurns(users);
        processDealerTurn();
        showCardResult(users);
        List<UserProfit> userProfits = gameService.settleResult(roundBetInfos, dealer);
        showGameRecord(userProfits);
        processTotalProfit(userProfits);
    }

    private List<RoundBetInfo> setUpRoundBetInfos() {
        String input = inputView.readUsers();
        List<String> names = InputParser.parseToList(input);
        List<Integer> betAmounts = inputView.readBetAmounts(names);
        return InputParser.parseToRoundBetInfos(names, betAmounts, INITIAL_ROUND);
    }

    private List<User> extractUsers(List<RoundBetInfo> roundBetInfos) {
        return roundBetInfos.stream()
                .map(RoundBetInfo::user)
                .toList();
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

    private void showGameRecord(List<UserProfit> userProfits) {
        outputView.printGameRecord(userProfits, dealer);
    }

    private void processTotalProfit(List<UserProfit> userProfits) {
        DealerProfit dealerProfit = gameService.upsertDealerProfit(userProfits);
        outputView.printTotalProfit(userProfits, dealerProfit);
    }
}
