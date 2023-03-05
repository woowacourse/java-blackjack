import java.util.List;
import java.util.stream.Collectors;

import domain.Dealer;
import domain.Deck;
import domain.Game;
import domain.Player;
import domain.Players;
import view.InputView;
import view.OutputView;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        List<Player> players = createPlayersWith(inputView.readNames());
        Game game = new Game(players, new Deck(), new Dealer());

        start(game);
        play(game);
        printResult(game);
    }

    private static void start(Game game) {
        Players players = game.getPlayers();
        game.dealCardsTwice();

        outputView.printDealStatus(players.getUsers());
        outputView.printFirstCardOf(players.getDealer());
        outputView.printPlayersStatus(players.getUsers());
    }

    private static void play(Game game) {
        for (Player user : game.getUsers()) {
            selectHitOrStand(game, user);
        }
        dealCardToDealer(game);
    }

    private static void printResult(Game game) {
        Players players = game.getPlayers();
        outputView.printCardsAndScores(players.getDealer(), game.getUsers());
        outputView.printDealerResults(game.getDealerResults());
        for (Player user : game.getUsers()) {
            String name = user.getName();
            outputView.printResult(name, game.getResult(name));
        }
    }

    private static void dealCardToDealer(Game game) {
        if (game.dealCardToDealer()) {
            outputView.noticeDealerHit();
            return;
        }
        outputView.noticeDealerStand();
    }

    private static void selectHitOrStand(Game game, Player player) {
        boolean hit = true;
        while (hit && player.canHit()) {
            hit = dealCardIfHit(game, player);
        }
    }

    private static boolean dealCardIfHit(Game game, Player player) {
        if (inputView.askForHit(player.getName())) {
            game.dealCardTo(player.getName());
            outputView.printPlayerCards(player.getName(), player.getCards());
            return true;
        }
        return false;
    }

    private static List<Player> createPlayersWith(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
