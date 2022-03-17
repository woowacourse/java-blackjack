import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {
    private static final int INIT_CARD_COUNT = 2;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final var dealer = new Dealer();
        final var players = new Players(InputView.inputPlayerName());
        initCards(dealer, players);
        hit(players, dealer);
        showCardsTotal(dealer, players);
        showGameResult(dealer, players);
    }

    static private void initCards(final Dealer dealer, final Players players) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            dealer.drawCard(CardDeck.draw());
            players.drawCard();
        }
        OutputView.printInitCardsResult(dealer, players.getPlayers());
    }

    static private void hit(final Players players, final Dealer dealer) {
        for (final Player player : players.getPlayers()) {
            hitPlayer(player);
        }
        hitDealer(dealer);
    }

    static private void hitPlayer(final Player player) {
        int printCount = 0;
        while (player.canDrawCard() && InputView.inputTryToHit(player)) {
            player.drawCard(CardDeck.draw());
            OutputView.printPlayerCards(player);
            printCount++;
        }
        if (printCount == 0) {
            OutputView.printPlayerCards(player);
        }
    }

    static private void hitDealer(final Dealer dealer) {
        final boolean hitResult = dealer.drawCard(CardDeck.draw());
        OutputView.printDealerHit(hitResult);
    }

    static private void showCardsTotal(final Dealer dealer, final Players players) {
        OutputView.printTotalScore(dealer, players.getPlayers());
    }

    static private void showGameResult(final Dealer dealer, final Players players) {
        OutputView.printGameResult(dealer, players.getPlayers());
    }
}
