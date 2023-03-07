import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        Game game = createGame(getPlayers());

        start(game);
        play(game);
        printResult(game);
    }

    private static Game createGame(List<Player> players) {
        return new Game(players, new Deck(), new Dealer());
    }

    private static List<Player> getPlayers() {
        List<String> playerNames = inputView.readNames();
        return createPlayersWith(playerNames);
    }

    private static void start(Game game) {
        Users users = game.getUsers();
        game.dealTwoCards();
        outputView.printCardsFrom(users);
    }

    private static void play(Game game) {
        for (Player user : game.getPlayers()) {
            selectHitAndStand(game, user);
        }
        dealCardToDealer(game);
    }

    private static void printResult(Game game) {
        Users users = game.getUsers();
        outputView.printCardsAndScores(users);
        outputView.printResultNotice();
        outputView.printDealerResults(users.getDealerResults());
        for (Player player : users.getPlayers()) {
            String name = player.getName();
            outputView.printResult(name, users.getUserResult(player));
        }
    }

    private static void dealCardToDealer(Game game) {
        if (game.canHitByDealerScore()) {
            game.dealCardToDealer();
            outputView.noticeDealerAccept();
            return;
        }
        outputView.noticeDealerDecline();
    }

    private static void selectHitAndStand(Game game, Player player) {
        boolean hit = true;
        while (hit && game.canHitByPlayerScore(player)) {
            hit = dealAnotherCardIfHit(game, player);
        }
    }

    private static boolean dealAnotherCardIfHit(Game game, Player player) {
        if (inputView.askForAnotherCard(player.getName())) {
            game.dealCard(player);
            outputView.printPlayerCards(player);
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
