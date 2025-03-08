package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.card.Deck;
import blackjack.model.Game;
import blackjack.model.Name;
import blackjack.model.Player;
import blackjack.model.card.RandomCardShuffler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = getPlayers();
        Game game = createGame(players);
        dealInitialHand(game);
        askHitForAllPlayer(game);
        dealerHitOrNot(game);
        displayResult(game);
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(name -> new Player(new Name(name), inputView::readHitOrNot))
                .toList();
    }

    private Game createGame(List<Player> players) {
        Dealer dealer = new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
        return new Game(dealer, players);
    }

    private void dealInitialHand(Game game) {
        game.dealInitialCards();
        outputView.printInitialCards(game.getDealerVisibleCard(), game.getPlayers());
    }

    private void askHitForAllPlayer(Game game) {
        game.askHitForAllPlayer(outputView::printPlayerHand);
    }

    private void dealerHitOrNot(Game game) {
        boolean isDealerHit = game.dealerHit();
        outputView.printDealerHit(isDealerHit);
    }

    private void displayResult(Game game) {
        outputView.printDealerHandAndTotal(game.getDealerHand(), game.getDealerTotal());
        outputView.printPlayerHandAndTotal(game.getPlayers());
        outputView.printMatchResult(game.judgeMatchResults());
    }
}
