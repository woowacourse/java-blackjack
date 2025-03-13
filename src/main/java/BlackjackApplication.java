import controller.Game;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        final InputView inputView = new InputView(new Scanner(System.in));
        final OutputView outputView = new OutputView();

        final Game game = Game.of(inputView, outputView);

        game.run();
    }
}
