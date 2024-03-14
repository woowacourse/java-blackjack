import domain.BlackJackResult;
import domain.Dealer;
import domain.Deck;
import domain.Name;
import domain.Player;
import domain.Players;
import java.util.List;
import util.ConsoleReader;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    private static final ConsoleReader CONSOLE_READER = new ConsoleReader();
    private static final String COMMAND_YES = "y";
    private static final int INITIAL_CARD_COUNT = 2;

    public static void main(String[] args) {
        Deck deck = Deck.withFullCards();
        List<Name> names = setPlayerNames();
        Players players = setPlayers(names);
        Dealer dealer = new Dealer();
        playInitialStep(dealer, players, deck);
        playHitStep(dealer, players, deck);
        playFinalStep(dealer, players);
    }

    private static List<Name> setPlayerNames() {
        List<String> inputs = InputView.readNames(CONSOLE_READER);
        return inputs.stream()
                .map(Name::new)
                .toList();
    }

    private static Players setPlayers(List<Name> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private static void playInitialStep(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            player.pickCards(deck, INITIAL_CARD_COUNT);
        }
        dealer.pickCards(deck, INITIAL_CARD_COUNT);

        OutputView.printDealerInitialCards(dealer.getCards());
        printInitialPlayersCards(players);
    }

    private static void printInitialPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerCards(player.getName(), player.getCards());
        }
        OutputView.printNewLine();
    }

    private static void playHitStep(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitPlayerStep(player, deck);
        }
        hitDealerStep(dealer, deck);
    }

    private static void hitPlayerStep(Player player, Deck deck) {
        boolean isFirstTurn = true;
        String answer = "y";
        while (isHittable(player, answer)) {
            answer = InputView.readAnswer(CONSOLE_READER, player.getName().name());
            hitByAnswer(player, deck, answer);
            printPlayerHitResult(answer, player, isFirstTurn);
            isFirstTurn = false;
        }
    }

    private static boolean isHittable(Player player, String answer) {
        return player.canHit() && COMMAND_YES.equals(answer);
    }

    private static void hitByAnswer(Player player, Deck deck, String answer) {
        if (COMMAND_YES.equals(answer)) {
            player.hit(deck);
        }
    }

    private static void printPlayerHitResult(String answer, Player player, boolean isFirstTurn) {
        if (isFirstTurn || COMMAND_YES.equals(answer)) {
            OutputView.printPlayerCards(player.getName(), player.getCards());
        }
    }

    private static void hitDealerStep(Dealer dealer, Deck deck) {
        int dealerDrawCount = 0;
        while (dealer.canHit()) {
            dealerDrawCount += dealer.hit(deck);
        }
        OutputView.printDealerHitCount(dealerDrawCount);
    }

    private static void playFinalStep(Dealer dealer, Players players) {
        printDealerStatus(dealer);
        printPlayersStatus(players);
        OutputView.printGameResult(new BlackJackResult(dealer, players));
    }

    private static void printDealerStatus(Dealer dealer) {
        OutputView.printDealerStatus(dealer.getCards(), dealer.getTotalScore());
    }

    private static void printPlayersStatus(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerStatus(player.getName(), player.getCards(), player.getTotalScore());
        }
    }
}
