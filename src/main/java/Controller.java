import domain.Game;
import domain.TotalDeck;
import domain.TotalDeckGenerator;
import domain.user.User;
import domain.user.Users;
import view.InputView;
import view.ResultView;

public class Controller {

    public void play() {
        Users users = InputView.inputNames();
        Game game = new Game(new TotalDeck(TotalDeckGenerator.generate()), users);
        ResultView.showStartStatus(users);
        run(game);
        showGameResult(users, game);
    }

    private void run(Game game) {
        while (game.continueInput()) {
            hitOrStayOnce(game);
        }

        while (game.addDealerCardCondition()) {
            game.addDealerCard();
            ResultView.dealerHit();
        }
    }

    private void hitOrStayOnce(Game game) {
        User currentPlayer = game.getCurrentPlayer();
        String command = InputView.inputAddCommand(currentPlayer.getName());

        switch (game.hitOrStay(command)) {
            case HIT -> ResultView.printPlayerAndDeck(currentPlayer);
            case BUST -> ResultView.printBust(currentPlayer);
        }
    }

    private void showGameResult(Users users, Game game) {
        ResultView.showCardsAndSum(users);
        ResultView.showResult(game);
    }
}
