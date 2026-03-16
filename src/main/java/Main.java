import domain.BlackjackGame;
import java.util.Scanner;
import view.InputView;
import view.ResultView;

public class Main {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(new Scanner(System.in)),
                new ResultView(),
                new BlackjackGame());
        blackjackController.run();
    }
}
