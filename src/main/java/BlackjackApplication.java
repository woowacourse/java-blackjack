import domain.BlackjackGameBoard;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Users;
import view.InputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGameManager blackjackGameManager = new BlackjackGameManager(
                new BlackjackGameBoard(DeckGenerator.generateDeck())
        );
        blackjackGameManager.startGame(
                Dealer.createDefaultDealer(),
                Users.from(InputView.inputUsers())
        );
    }
}
