package second;

import second.domain.card.CardDeck;
import second.domain.player.AllGamers;
import second.domain.player.Dealer;
import second.domain.player.Player;
import second.domain.result.Results;
import second.view.InputView;
import second.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {
    public static void main(String[] args) {
        List<Player> players = inputPlayerNames();
        AllGamers allGamers = new AllGamers(players);

        CardDeck cardDeck = new CardDeck();
        OutputView.printEmptyLine();

        doFirstDrawPhase(allGamers, cardDeck);

        doDrawMorePhase(allGamers, cardDeck);

        doResultsPhase(allGamers);
    }

    private static List<Player> inputPlayerNames() {
        return InputView.inputPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void doFirstDrawPhase(AllGamers allGamers, CardDeck cardDeck) {
        allGamers.drawFirstPhase(cardDeck);
        OutputView.printInitialCards(allGamers);
        OutputView.printEmptyLine();
    }

    private static void doDrawMorePhase(AllGamers allGamers, CardDeck cardDeck) {
        askPlayersDrawMore(allGamers.getPlayers(), cardDeck);
        OutputView.printEmptyLine();

        determineDealerMoreDraw(allGamers.getDealer(), cardDeck);
        OutputView.printEmptyLine();
    }


    private static void determineDealerMoreDraw(Dealer dealer, CardDeck cardDeck) {
        if (dealer.canDrawMore()) {
            OutputView.printDealerCanDrawMore();
            dealer.drawCard(cardDeck);
        }
    }

    private static void askPlayersDrawMore(List<Player> players, CardDeck cardDeck) {
        for (Player player : players) {
            drawIfWant(cardDeck, player);
        }
    }

    private static void drawIfWant(CardDeck cardDeck, Player player) {
        while (canDrawMore(player)) {
            if (InputView.askDrawMore(player) == false) {
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

    private static void doResultsPhase(AllGamers allGamers) {
        printScores(allGamers.getDealer(), allGamers.getPlayers());
        printResults(allGamers.getDealer(), allGamers.getPlayers());
    }

    private static void printScores(Dealer dealer, List<Player> players) {
        OutputView.printScore(dealer);
        players.forEach(OutputView::printScore);

        OutputView.printEmptyLine();
    }

    private static void printResults(Dealer dealer, List<Player> players) {
        Results results = new Results(players, dealer);

        OutputView.printResults(results);
    }
}
