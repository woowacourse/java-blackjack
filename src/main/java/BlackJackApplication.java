import domain.card.providable.CardDeck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.result.Results;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {
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
        dealer.drawInitialCards(cardDeck);
        players.forEach(player -> player.drawInitialCards(cardDeck));

        OutputView.printInitialCards(dealer, players);
        OutputView.printEmptyLine();
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
        Results results = new Results(players, dealer);

        OutputView.printResults(results);
    }
}
