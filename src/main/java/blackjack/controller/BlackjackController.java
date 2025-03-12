package blackjack.controller;

import blackjack.model.Game;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
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
        Game game = createGame(players);
        dealInitialHand(game);
        askHitForAllPlayer(game);
        dealerHitOrNot(game);
        displayResult(game);
    }

    private Game createGame(List<Player> players) {
        Deck deck = Deck.createShuffledDeck(Card.createDeck(), new RandomCardShuffler());
        Dealer dealer = new Dealer();
        return new Game(deck, dealer, players);
    }

    private void dealInitialHand(Game game) {
        game.dealInitialCards();
        outputView.printInitialCards(game.getDealerVisibleCard(), game.getPlayers());
    }

    private void askHitForAllPlayer(Game game) {
        game.askHitForAllPlayer(inputView::readHitOrNot, outputView::printPlayerHand);
    }

    private void dealerHitOrNot(Game game) {
        boolean isDealerHit = game.dealerHit();
        outputView.printDealerHit(isDealerHit);
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void displayResult(Game game) {
        outputView.printDealerHandAndTotal(game.getDealerHand(), game.getDealerTotal());
        outputView.printPlayerHandAndTotal(game.getPlayers());
        outputView.printMatchResult(game.judgeMatchResults());
    }
}
