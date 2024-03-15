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
        Judge judge = new Judge();

        setUpPlayers(game, judge);

        setUpGame(game);

        progressGame(game);

        makeResult(game, judge);
    }

    private static BlackJackGame startGame() {
        List<String> rawPlayersNames = inputView.readPlayersNames();
        return new BlackJackGame(rawPlayersNames);
    }

    private static void setUpPlayers(BlackJackGame game, Judge judge) {
        for (Player player : game.getGamers().getPlayers()) {
            int bettingAmount = inputView.readBettingAmount(player);
            judge.initializeProfit(player, new Profit(bettingAmount));
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
        HitOption doPlayerHit = HitOption.HIT;
        while (player.isNotBust() && doPlayerHit.isHit()) {
            HitOption hitOption = inputView.readHitOption(player);
            doPlayerHit = game.hitByPlayer(hitOption, player);
            printPlayerHitCard(player, doPlayerHit);
        }
    }

    private static void printPlayerHitCard(Player player, HitOption doPlayerHit) {
        if (doPlayerHit.isHit()) {
            resultView.printPlayerCards(player);
        }
    }

    private static void progressDealerGame(BlackJackGame game, Dealer dealer) {
        while (dealer.canHit()) {
            Card pickedCard = game.hitByDealer(dealer);
            resultView.printDealerHitMessage(dealer, pickedCard);
        }
    }

    private static void makeResult(BlackJackGame game, Judge judge) {
        Gamers gamers = game.getGamers();
        judge.decideResult(gamers);
        resultView.printFinalProfit(gamers.getDealer(), judge);
    }
}
