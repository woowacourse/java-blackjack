import domain.BlackJackGame;
import domain.HitOption;
import domain.Profit;
import domain.cards.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.result.Judge;
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
            int battingAmount = inputView.readBattingAmount(player);
            game.initializeProfit(player, new Profit(battingAmount));
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
        boolean doPlayerHit = true;
        while (player.isNotBust() && doPlayerHit) {
            HitOption hitOption = inputView.readHitOption(player);
            doPlayerHit = game.hitByPlayer(hitOption, player);
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

    private static void makeResult(BlackJackGame game) {
        Gamers gamers = game.getGamers();
        Judge judge = game.makeFinalResult();
        resultView.printFinalProfit(gamers.getDealer(), judge);
    }
}
