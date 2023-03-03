import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        List<String> playerNames = inputView.readNames();
        List<Player> players = createPlayersWith(playerNames);
        Game game = new Game(players, new Deck(), new Dealer());

        start(game);
        play(playerNames, game);
        printResult(game);
    }

    private static void start(Game game) {
        Players players = game.getPlayers();
        game.dealCardsTwice();

        outputView.printDealCards(players.getUsers());
        outputView.printFirstPlayerCard(players.getDealer());
        outputView.printPlayersCards(players.getUsers());
    }

    private static void play(List<String> playerNames, Game game) {
        for (String playerName : playerNames) {
            selectHitOrStand(game, playerName);
        }
        dealCardToDealer(game);
    }

    private static void printResult(Game game) {
        Players players = game.getPlayers();
        outputView.printCardsAndScores(players);
        System.out.println("## 최종 승패");
        outputView.printDealerResults(game.getDealerResults());
        for (Player user : players.getUsers()) {
            String name = user.getName();
            outputView.printResult(name, game.getResult(name));
        }
    }

    private static void dealCardToDealer(Game game) {
        if (game.dealCardToDealer()) {
            outputView.noticeDealerAccept();
            return;
        }
        outputView.noticeDealerDecline();
    }

    private static void selectHitOrStand(Game game, String playerName) {
        boolean hit = true;
        while (hit && game.canHit(playerName)) {
            hit = dealCardIfHit(game, playerName);
        }
    }

    private static boolean dealCardIfHit(Game game, String playerName) {
        if (inputView.askForHit(playerName)) {
            game.dealCardTo(playerName);
            outputView.printPlayerCards(playerName, game.getCards(playerName));
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
