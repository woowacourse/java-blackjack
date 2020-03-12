import domain.card.providable.CardDeck;
import domain.gamer.AllGamers;
import domain.gamer.Dealer;
import domain.gamer.Player;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {
    public static void main(String[] args) {
        List<Player> players = inputPlayerNames();
        Dealer dealer = new Dealer();
        AllGamers allGamers = new AllGamers(dealer, players);
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
        detetermineDealerMoreDraw(allGamers.getDealer(), cardDeck);
        OutputView.printEmptyLine();
    }

    private static void detetermineDealerMoreDraw(Dealer dealer, CardDeck cardDeck) {
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

    private static void doResultsPhase(AllGamers allGamers) {
        OutputView.printScore(allGamers);
        OutputView.printEmptyLine();
        OutputView.printReults(allGamers.determineResults());
    }
}
