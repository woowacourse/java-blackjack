import domain.BlackJackGame;
import domain.Judge;
import domain.cards.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import view.InputView;
import view.ResultView;

public class Application { // TODO: refactoring

    private static final InputView inputView = new InputView();
    private static final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        List<String> rawPlayersNames = inputView.readPlayersNames();

        BlackJackGame game = new BlackJackGame(rawPlayersNames);

        Gamers gamers = game.getGamers();

        game.setUpGame();
        resultView.printInitialCards(gamers.getDealer(), gamers.getPlayers());

        progressPlayersGame(game, gamers.getPlayers());
        progressDealerGame(game, gamers.getDealer());
        resultView.printGamersCardsScore(gamers);

        Judge judge = game.makeFinalResult();
        resultView.printFinalResult(gamers.getDealer(), judge);
    }

    private static void progressPlayersGame(BlackJackGame game, List<Player> players) {
        for (Player player : players) {
            progressPlayerGame(game, player);
        }
    }

    private static void progressPlayerGame(BlackJackGame game, Player player) {
        boolean doPlayerHit = true;
        while (player.isNotBust() && doPlayerHit) {
            String rawHitOption = inputView.readHitOrNot(player);
            doPlayerHit = game.hitByPlayer(rawHitOption, player);
            printPlayerHitCard(player, doPlayerHit);
        }
    }

    private static void printPlayerHitCard(Player player, boolean doPlayerHit) {
        if (doPlayerHit) {
            resultView.printPlayerCards(player);
        }
    }

    private static void progressDealerGame(BlackJackGame game, Dealer dealer) {
        while (dealer.canHit()) {
            Card pickedCard = game.hitByDealer(dealer);
            resultView.printDealerHitMessage(dealer, pickedCard);
        }
    }
}
