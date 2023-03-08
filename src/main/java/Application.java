import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Dealer;
import domain.Deck;
import domain.Game;
import domain.Player;
import domain.User;
import dto.PlayerDto;
import view.InputView;
import view.OutputView;

public class Application {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    public static void main(String[] args) {
        List<User> users = createUsersFrom(INPUT_VIEW.readNames());
        Game game = new Game(new Dealer(), users, new Deck());

        start(game);
        play(game);

        printCardsAndScores(game);
        printResult(game);
    }

    private static List<User> createUsersFrom(List<String> names) {
        return names.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    private static void start(Game game) {
        game.dealTwice();

        OUTPUT_VIEW.printDealStatus(createDtosOf(game.getUsers()));
        OUTPUT_VIEW.printFirstCardOfDealer(game.getDealer().hand());
        OUTPUT_VIEW.printUsersStatus(createDtosOf(game.getUsers()));
    }

    private static void play(Game game) {
        for (var user : game.getUsers()) {
            selectHitOrStand(game, user);
        }
        selectHitOrStand(game, game.getDealer());
    }

    private static void printCardsAndScores(Game game) {
        List<Player> players = joinPlayers(game.getDealer(), game.getUsers());
        OUTPUT_VIEW.printCardsAndScores(createDtosOf(players));
    }

    private static void printResult(Game game) {
        OUTPUT_VIEW.printDealerResults(game.getDealerResults());
        for (var user : game.getUsers()) {
            OUTPUT_VIEW.printResult(user.getName(), game.getResultOf(user));
        }
    }

    private static void selectHitOrStand(Game game, User user) {
        while (user.canHit() && INPUT_VIEW.askForHit(user.getName())) {
            game.dealTo(user);
            OUTPUT_VIEW.printPlayerHand(user.getName(), user.hand());
        }
    }

    private static void selectHitOrStand(Game game, Dealer dealer) {
        int hitCount = 0;
        while (dealer.canHit()) {
            game.dealTo(dealer);
            ++hitCount;
        }
        OUTPUT_VIEW.noticeDealerHitOrStand(hitCount);
    }

    private static List<Player> joinPlayers(Dealer dealer, List<User> users) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(users);
        return players;
    }

    private static List<PlayerDto> createDtosOf(List<? extends Player> players) {
        return players
                .stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());
    }
}
