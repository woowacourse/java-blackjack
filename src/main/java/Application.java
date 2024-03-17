import domain.BlackJackGame;
import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import view.InputView;
import view.ResultView;

public class Application {

    private static final InputView inputView = new InputView();
    private static final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        BlackJackGame game = startGame();

        setUpPlayers(game);

        setUpGame(game);

        progressGame(game);

        makeResult(game);
    }

    private static BlackJackGame startGame() {
        List<String> rawPlayersNames = inputView.readPlayersNames();
        return new BlackJackGame(rawPlayersNames);
    }

    private static void setUpPlayers(BlackJackGame game) {
        for (Player player : game.getGamers().getPlayers()) {
            int bettingAmount = inputView.readBettingAmount(player);
            game.setUpProfits(player, bettingAmount);
        }
    }

    private static void setUpGame(BlackJackGame game) {
        game.setUpGame();
        Gamers gamers = game.getGamers();
        resultView.printInitialCards(gamers.getDealer(), gamers.getPlayers());
    }

    private static void progressGame(BlackJackGame game) {
        Gamers gamers = game.getGamers();
        progressPlayersGame(game, gamers.getPlayers());
        progressDealerGame(game, gamers.getDealer());
        resultView.printGamersCardsScore(gamers);
    }

    private static void progressPlayersGame(BlackJackGame game, List<Player> players) {
        for (Player player : players) {
            progressPlayerGame(game, player);
        }
    }

    private static void progressPlayerGame(BlackJackGame game, Player player) {
        while (player.isNotBust() && inputView.readHitOption(player).isHit()) {
            game.hitByPlayer(player);
            printPlayerHitCard(player);
        }
    }

    private static void printPlayerHitCard(Player player) {
        resultView.printPlayerCards(player);
    }

    private static void progressDealerGame(BlackJackGame game, Dealer dealer) {
        while (dealer.canHit()) {
            Card pickedCard = game.hitByDealer(dealer);
            resultView.printDealerHitMessage(dealer, pickedCard);
        }
    }

    private static void makeResult(BlackJackGame game) {
        game.makeResult();
        resultView.printFinalProfit(game.getGamers(), game.getJudge());
    }
}
