import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.Players;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.inputNames());
        CardDeck cardDeck = new CardDeck();
        try {
            distributeFirstCards(dealer, players, cardDeck);
            drawMoreCards(dealer, players, cardDeck);
            printCalculatedResult(dealer, players);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printCalculatedResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            player.calculateWinningResult(dealer.getCards().getScore());
        }
        OutputView.printUsersCards(dealer, players, true);
        OutputView.printFinalResult(players);
    }

    private static void drawMoreCards(Dealer dealer, Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawMorePlayerCard(cardDeck, player);
        }
        while (dealer.canHit()) {
            OutputView.printDealerHitMessage();
            dealer.drawCard(cardDeck);
        }
    }

    private static void distributeFirstCards(Dealer dealer, Players players, CardDeck cardDeck) {
        dealer.drawCard(cardDeck, 2);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, 2);
        }
        OutputView.printFirstCards(dealer, players);
    }

    private static void drawMorePlayerCard(CardDeck cardDeck, Player player) {
        while (player.canHit() && InputView.inputMoreCard(player)) {
            player.drawCard(cardDeck);
            OutputView.printUserCards(player.getName(), player, false);
        }
    }
}
