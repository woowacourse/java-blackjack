package blackjack;

import java.util.List;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        List<Player> players = getPlayers();
        Game game = initializeGame(players);
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private static Game initializeGame(List<Player> players) {
        Dealer dealer = new Dealer(Deck.createShuffledDeck(Card.createDeck(), new RandomCardShuffler()));
        return new Game(dealer, players);
    }
}
