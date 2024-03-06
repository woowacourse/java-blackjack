import domain.Game;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.user.Users;
import view.InputView;
import view.ResultView;

public class Controller {

    public void play() {
        Users users = InputView.inputNames();

        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        ResultView.showProcess(users);

        while (game.continueInput()) {
            String command = InputView.inputAddCommand(game.getCurrentPlayer().getName());
            game.doOrDie(command);
            if ("y".equals(command)) {
                ResultView.printPlayerAndDeck(game.getCurrentPlayer());
            }
        }

        while (game.addDealerCardCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }

        ResultView.showCardsAndSum(users);
        ResultView.showResult(game);
    }
}
