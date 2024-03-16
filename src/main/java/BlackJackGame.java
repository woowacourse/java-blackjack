import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;
import domain.result.BettingResult;
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
        OutputView.printInitialStep(names);
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
                .map(BlackJackGame::setBettingMoney)
                .toList();
        return new Players(players);
    }

    private static Player setBettingMoney(Name name) {
        double input = InputView.readBettingMoney(CONSOLE_READER, name.name());
        OutputView.printNewLine();
        return new Player(name, new Money(input));
    }

    private static void playInitialStep(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            player.pickCards(deck, INITIAL_CARD_COUNT);
        }
        dealer.pickCards(deck, INITIAL_CARD_COUNT);
        OutputView.printGamerHiddenCards(dealer);
        printInitialPlayersCards(players);
    }

    private static void printInitialPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printGamerCards(player);
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
            OutputView.printGamerCards(player);
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
        OutputView.printGamerStatus(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printGamerStatus(player);
        }
        OutputView.printBettingResult(new BettingResult(dealer, players));
    }
}
