import domain.*;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    public static final int FIRST_CARD_COUNT = 2;

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Names names = new Names(InputView.inputNames());
        Players players = new Players(names);
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
            WinningResult winningResult = player.calculateWinningResult(dealer.getCards().getScore());
            dealer.reflectWinningResult(winningResult);
        }
        OutputView.printUsersCards(dealer, players, true);
        OutputView.printFinalResult(dealer, players);
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
        dealer.drawCard(cardDeck, FIRST_CARD_COUNT);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, FIRST_CARD_COUNT);
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
