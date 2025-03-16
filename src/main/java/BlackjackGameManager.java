import domain.BlackjackGameBoard;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.profit.DefaultProfitStrategy;
import view.InputView;
import view.OutputView;

public class BlackjackGameManager {

    private final Dealer dealer;
    private final Users users;

    public BlackjackGameManager() {
        this.dealer = Dealer.createDefaultDealer();
        this.users = Users.from(InputView.inputUsers());
    }

    public void startGame() {
        BlackjackGameBoard blackjackGameBoard = new BlackjackGameBoard(DeckGenerator.generateDeck());

        initialCardsDistribution(blackjackGameBoard);
        hitOrStayPhase(blackjackGameBoard);
        printPlayerCardsAndSum(blackjackGameBoard);
        printPlayerProfit(blackjackGameBoard);
    }

    private void initialCardsDistribution(BlackjackGameBoard blackjackGameBoard) {
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

    private void hitOrStayPhase(BlackjackGameBoard blackjackGameBoard) {
        for (User user : users.getUsers()) {
            blackjackGameBoard.hitUntilStay(user, InputView::inputWantHit, OutputView::printPlayerCards);
        }
        blackjackGameBoard.hitUntilUnder16(dealer, OutputView::printDealerHitMessage);
    }

    private void printPlayerCardsAndSum(BlackjackGameBoard blackjackGameBoard) {
        OutputView.printPlayerCardsAndSum(dealer.getName(),
                blackjackGameBoard.playerCards(dealer), blackjackGameBoard.playerCardsSum(dealer));
        for (User user : users.getUsers()) {
            OutputView.printPlayerCardsAndSum(user.getName(),
                    blackjackGameBoard.playerCards(user), blackjackGameBoard.playerCardsSum(user));
        }
    }

    private void printPlayerProfit(BlackjackGameBoard blackjackGameBoard) {
        OutputView.printPlayerProfit(dealer.getName(),
                blackjackGameBoard.computeDealerProfit(dealer, users, DefaultProfitStrategy.getInstance()));

        for (User user : users.getUsers()) {
            OutputView.printPlayerProfit(user.getName(),
                    blackjackGameBoard.computeUserProfit(user, dealer, DefaultProfitStrategy.getInstance())
            );
        }
    }
}
