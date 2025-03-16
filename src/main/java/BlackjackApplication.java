import domain.player.Dealer;
import domain.player.Users;
import view.InputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGameManager blackjackGameManager = new BlackjackGameManager(
                Dealer.createDefaultDealer(),
                Users.from(InputView.inputUsers())
        );
        blackjackGameManager.startGame();
    }
}
