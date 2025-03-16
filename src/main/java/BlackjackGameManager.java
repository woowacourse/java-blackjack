import domain.BlackjackGameBoard;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
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
        // TODO: 수익 출력
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
        blackjackGameBoard.hitUntilUnder16(dealer);
    }

    private void printPlayerCardsAndSum(BlackjackGameBoard blackjackGameBoard) {
        OutputView.printPlayerCardsAndSum(dealer.getName(),
                blackjackGameBoard.playerCards(dealer), blackjackGameBoard.playerCardsSum(dealer));
        for (User user : users.getUsers()) {
            OutputView.printPlayerCardsAndSum(user.getName(),
                    blackjackGameBoard.playerCards(user), blackjackGameBoard.playerCardsSum(user));
        }
    }
}
