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

        runFirstDrawPhase(allGamers, cardDeck);

        askDrawMore(allGamers, cardDeck);
    }

    private static List<Player> inputPlayerNames() {
        return InputView.inputPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void runFirstDrawPhase(AllGamers allGamers, CardDeck cardDeck) {
        allGamers.drawFirstPhase(cardDeck);
        OutputView.printInitialCards(allGamers);
    }

    private static void askDrawMore(AllGamers allGamers, CardDeck cardDeck) {
        List<Player> players = allGamers.getPlayers();
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
            OutputView.printCanNotDrawMessage();
        }
        return player.canDrawMore();
    }
}
