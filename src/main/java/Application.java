import model.Game;
import model.Player;
import view.InputView;
import view.ResultView;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        String[] playerNames = InputView.getPlayerNames();
        Game game = new Game(Arrays.asList(playerNames));
        game.start();
        ResultView.showInitialCards(game.getDealer(), game.getPlayers());

        for (Player player : game.getPlayers()) {
            while (player.getCardsValue() < 21 && InputView.wantsCard(player.getName())) {
                player.receiveCard(game.getDeck().dealCard());
                System.out.println(player.getName() + " 카드: " + ResultView.formatCards(player.getCards()));
            }
            System.out.println();
        }

        game.play();
        ResultView.showFinalResults(game.getDealer(), game.getPlayers());
        ResultView.showFinalOutcome(game.getDealer(), game.getPlayers());
    }
}