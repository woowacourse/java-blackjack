package blackjack;

import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = getPlayers();
        Game game = initializeGame(players);
        game.dealInitialCards();
        outputView.printInitialCards(game.getDealerVisibleCard(), game.getPlayers());
        game.askHitForAllPlayer(inputView::readHitOrNot, outputView::printPlayerHand);
        boolean isDealerHit = game.dealerHit();
        outputView.printDealerHit(isDealerHit);

        outputView.printDealerHandAndTotal(game.getDealerHand(), game.getDealerTotal());
        outputView.printPlayerHandAndTotal(game.getPlayers());
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private Game initializeGame(List<Player> players) {
        Dealer dealer = new Dealer(Deck.createShuffledDeck(Card.createDeck(), new RandomCardShuffler()));
        return new Game(dealer, players);
    }
}
