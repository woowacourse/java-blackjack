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
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    public static void main(String[] args) {
        List<Player> players = createPlayersWith(INPUT_VIEW.readNames());
        Game game = new Game(players, new Deck(), new Dealer());

        start(game);
        play(game);
        printResult(game);
    }

    private static void start(Game game) {
        Players players = game.getPlayers();
        game.dealCardsTwice();

        OUTPUT_VIEW.printDealStatus(players.getUsers());
        OUTPUT_VIEW.printFirstCardOf(players.getDealer());
        OUTPUT_VIEW.printPlayersStatus(players.getUsers());
    }

    private static void play(Game game) {
        for (Player user : game.getUsers()) {
            selectHitOrStand(game, user);
        }
        dealCardToDealer(game);
    }

    private static void printResult(Game game) {
        Players players = game.getPlayers();
        OUTPUT_VIEW.printCardsAndScores(players.getDealer(), game.getUsers());
        OUTPUT_VIEW.printDealerResults(game.getDealerResults());
        for (Player user : game.getUsers()) {
            String name = user.getName();
            OUTPUT_VIEW.printResult(name, game.getResult(name));
        }
    }

    private static void dealCardToDealer(Game game) {
        if (game.dealCardToDealer()) {
            OUTPUT_VIEW.noticeDealerHit();
            return;
        }
        OUTPUT_VIEW.noticeDealerStand();
    }

    private static void selectHitOrStand(Game game, Player player) {
        boolean hit = true;
        while (hit && player.canHit()) {
            hit = dealCardIfHit(game, player);
        }
    }

    private static boolean dealCardIfHit(Game game, Player player) {
        if (INPUT_VIEW.askForHit(player.getName())) {
            game.dealCardTo(player.getName());
            OUTPUT_VIEW.printPlayerCards(player.getName(), player.getCards());
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
