import domain.card.providable.CardDeck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.result.WinLose;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class BlackJackApplication {
    private static final int INITIAL_CARDS_AMOUNT = 2;

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        List<Player> players = inputPlayerNames();
        OutputView.printEmptyLine();

        doInitialDrawPhase(dealer, players, cardDeck);

        doAdditionalDrawPhase(dealer, players, cardDeck);

        doResultsPhase(dealer, players);
    }

    private static List<Player> inputPlayerNames() {
        return InputView.inputPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void doInitialDrawPhase(Dealer dealer, List<Player> players, CardDeck cardDeck) {
        drawInitialCards(dealer, cardDeck);
        players.forEach(player -> drawInitialCards(player, cardDeck));

        OutputView.printInitialCards(dealer, players);
        OutputView.printEmptyLine();
    }

    private static void drawInitialCards(Gamer gamer, CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_CARDS_AMOUNT; i++) {
            gamer.drawCard(cardDeck);
        }
    }

    private static void doAdditionalDrawPhase(Dealer dealer, List<Player> players, CardDeck cardDeck) {
        askPlayersDrawMore(players, cardDeck);
        OutputView.printEmptyLine();

        determineDealerMoreDraw(dealer, cardDeck);
        OutputView.printEmptyLine();
    }

    private static void askPlayersDrawMore(List<Player> players, CardDeck cardDeck) {
        for (Player player : players) {
            askMoreDraw(player, cardDeck);
        }
    }

    private static void askMoreDraw(Player player, CardDeck cardDeck) {
        while (canDrawMore(player)) {
            if (!InputView.askDrawMore(player)) {
                OutputView.printGamerState(player);
                break;
            }

            player.drawCard(cardDeck);
            OutputView.printGamerState(player);
        }
    }

    private static boolean canDrawMore(Player player) {
        if (player.canDrawMore() == false) {
            OutputView.printCanNotDrawMessage(player);
        }

        return player.canDrawMore();
    }

    private static void determineDealerMoreDraw(Dealer dealer, CardDeck cardDeck) {
        if (dealer.canDrawMore()) {
            OutputView.printDealerCanDrawMore();
            dealer.drawCard(cardDeck);
        }
    }

    private static void doResultsPhase(Dealer dealer, List<Player> players) {
        printScores(dealer, players);
        printResults(dealer, players);
    }

    private static void printScores(Dealer dealer, List<Player> players) {
        OutputView.printScore(dealer);
        players.forEach(player -> OutputView.printScore(player));

        OutputView.printEmptyLine();
    }

    private static void printResults(Dealer dealer, List<Player> players) {
        List<WinLose> playerWinLoses = determinePlayerWinLoses(dealer, players);
        Map<WinLose, Integer> dealerWinLoses = determineDealerWinLoses(playerWinLoses);

        OutputView.printResults(dealerWinLoses, players, playerWinLoses);
    }

    private static List<WinLose> determinePlayerWinLoses(Dealer dealer, List<Player> players) {
        return players.stream()
                .map(player -> player.determineWinLose(dealer))
                .collect(Collectors.toList());
    }

    private static Map<WinLose, Integer> determineDealerWinLoses(List<WinLose> winLoses) {
        return winLoses.stream()
                .collect(groupingBy(WinLose::reverse, summingInt(x -> 1)));
    }
}
