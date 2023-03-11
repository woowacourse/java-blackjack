import domain.*;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Users;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

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

    private static List<Player> createPlayersWith(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            players.add(createPlayer(playerName));
        }

        return players;
    }

    private static Player createPlayer(String playerName) {
        int bettingAmount = inputView.enterBettingAmount(playerName);
        return new Player(playerName, new Money(bettingAmount));
    }

    private static void start(Game game) {
        Users users = game.getUsers();
        game.dealCardsInFirstTurn();
        outputView.printCardsFrom(users);
    }

    private static void play(Game game) {
        for (Player user : game.getPlayers()) {
            selectHitAndStand(game, user);
        }
        dealCardToDealer(game);
    }

    private static void selectHitAndStand(Game game, Player player) {
        while (game.canHitByPlayerScore(player)) {
            dealAnotherCardIfHit(game, player);
        }
    }

    private static void dealAnotherCardIfHit(Game game, Player player) {
        boolean isYes = inputView.askForAnotherCard(player.getName());

        if (isYes) {
            game.dealCard(player);
            outputView.printPlayerCards(player);
        }
        game.updateStatusToStay(isYes, player);
    }

    private static void dealCardToDealer(Game game) {
        if (game.canHitByDealerScore()) {
            game.dealCardToDealer();
            outputView.noticeDealerAccept();
            return;
        }
        outputView.noticeDealerDecline();
    }

    private static void printResult(Game game) {
        Users users = game.getUsers();
        outputView.printCardsAndScores(users);
        outputView.printResultNotice();
        outputView.printDealerResults(users.getDealerResults());
        for (Player player : users.players()) {
            String name = player.getName();
            outputView.printResult(name, users.getUserResult(player));
        }
    }

}
