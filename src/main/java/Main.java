import domain.BlackjackGame;
import domain.Rank;
import domain.Suit;
import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Player;
import view.InputView;
import view.ResultView;

public class Main {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new ResultView(), new BlackjackGame());
        blackjackController.run();
    }
}
