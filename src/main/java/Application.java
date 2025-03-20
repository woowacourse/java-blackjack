import card.CardHand;
import card.Deck;
import card.DeckGenerator;
import console.GamePlayConsole;
import console.GameResultConsole;
import console.GameSetupConsole;
import java.util.List;
import participant.Dealer;
import participant.Player;

public final class Application {
    private final GameSetupConsole gameSetupConsole = new GameSetupConsole();
    private final GamePlayConsole gamePlayConsole = new GamePlayConsole();
    private final GameResultConsole gameResultConsole = new GameResultConsole();

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        Deck deck = DeckGenerator.generateRandomDeck();
        Dealer dealer = new Dealer(CardHand.drawInitialHand(deck));
        List<Player> players = gameSetupConsole.registerPlayers(deck);
        gameSetupConsole.displaySetupResult(dealer, players);
        gamePlayConsole.drawAllPlayerCards(players, deck);
        gamePlayConsole.drawDealerCards(dealer, deck);
        gameResultConsole.displayFinalScores(dealer, players);
        gameResultConsole.displayFinalProfits(dealer, players);
    }
}
