import domain.Deck;
import domain.Game;
import domain.Participants;
import domain.User;
import view.InputView;
import view.OutputView;

public class Application {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    public static void main(String[] args) {
        Participants participants = Participants.of(INPUT_VIEW.readNames());
        Game game = new Game(participants, new Deck());

        start(game);
        play(game);
        printResult(game);
    }

    private static void start(Game game) {
        game.dealCardsTwice();

        OUTPUT_VIEW.printDealStatus(game.getUsers());
        OUTPUT_VIEW.printFirstCardOf(game.getDealer());
        OUTPUT_VIEW.printUsersStatus(game.getUsers());
    }

    private static void play(Game game) {
        for (User user : game.getUsers()) {
            selectHitOrStand(game, user);
        }
        dealCardToDealer(game);
    }

    private static void printResult(Game game) {
        OUTPUT_VIEW.printCardsAndScores(game.getPlayers());
        OUTPUT_VIEW.printDealerResults(game.getDealerResults());
        for (User user : game.getUsers()) {
            OUTPUT_VIEW.printResult(user, game.getResultOf(user));
        }
    }

    private static void dealCardToDealer(Game game) {
        if (game.dealCardToDealer()) {
            OUTPUT_VIEW.noticeDealerHit();
            return;
        }
        OUTPUT_VIEW.noticeDealerStand();
    }

    private static void selectHitOrStand(Game game, User user) {
        boolean hit = true;
        while (hit && user.canHit()) {
            hit = dealCardIfHit(game, user);
        }
    }

    private static boolean dealCardIfHit(Game game, User user) {
        if (INPUT_VIEW.askForHit(user)) {
            game.dealCardTo(user);
            OUTPUT_VIEW.printPlayerCards(user.getName(), user.getCards());
            return true;
        }
        return false;
    }
}
