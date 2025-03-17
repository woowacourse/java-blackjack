import domain.BlackjackGameBoard;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.profit.DefaultProfitStrategy;
import view.InputView;
import view.OutputView;

public class BlackjackGameManager {

    public BlackjackGameManager() {
    }

    public void startGame(BlackjackGameBoard blackjackGameBoard, Dealer dealer, Users users) {
        initialCardsDistribution(blackjackGameBoard, dealer, users);
        hitOrStayPhase(blackjackGameBoard, dealer, users);
        printPlayerCardsAndSum(blackjackGameBoard, dealer, users);
        printPlayerProfit(blackjackGameBoard, dealer, users);
    }

    private void initialCardsDistribution(BlackjackGameBoard blackjackGameBoard, Dealer dealer, Users users) {
        blackjackGameBoard.distributeInitialCards(dealer);
        for (User user : users.getUsers()) {
            blackjackGameBoard.distributeInitialCards(user);
        }

        blackjackGameBoard.openInitialCards(dealer);
        for (User user : users.getUsers()) {
            blackjackGameBoard.openInitialCards(user);
        }
        OutputView.printInitialCards(dealer, users);
    }

    private void hitOrStayPhase(BlackjackGameBoard blackjackGameBoard, Dealer dealer, Users users) {
        for (User user : users.getUsers()) {
            blackjackGameBoard.hitUntilStay(user, InputView::inputWantHit, OutputView::printPlayerCards);
        }
        blackjackGameBoard.hitUntilNotHittable(dealer, OutputView::printDealerHitMessage);
    }

    private void printPlayerCardsAndSum(BlackjackGameBoard blackjackGameBoard, Dealer dealer, Users users) {
        OutputView.printPlayerCardsAndSum(dealer.getName(),
                blackjackGameBoard.playerCards(dealer), blackjackGameBoard.playerCardsSum(dealer));
        for (User user : users.getUsers()) {
            OutputView.printPlayerCardsAndSum(user.getName(),
                    blackjackGameBoard.playerCards(user), blackjackGameBoard.playerCardsSum(user));
        }
    }

    private void printPlayerProfit(BlackjackGameBoard blackjackGameBoard, Dealer dealer, Users users) {
        OutputView.printPlayerProfit(dealer.getName(),
                blackjackGameBoard.computeDealerProfit(dealer, users, DefaultProfitStrategy.getInstance()));

        for (User user : users.getUsers()) {
            OutputView.printPlayerProfit(user.getName(),
                    blackjackGameBoard.computeUserProfit(user, dealer, DefaultProfitStrategy.getInstance())
            );
        }
    }
}
