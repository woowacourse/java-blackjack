package controller;

import domain.BlackJackGame;
import domain.card.Deck;
import domain.card.ShuffledCardsGenerator;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.PlayerResults;
import exception.CardReceiveException;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void start() {
        Dealer dealer = new Dealer();
        Players players = createPlayers();
        Deck deck = createDeck();
        BlackJackGame blackJackGame = new BlackJackGame(deck);
        blackJackGame.prepareCards(dealer, players);
        OutputView.printInitialCardsMessage(dealer, players);
        handOutCard(blackJackGame, dealer, players);
        OutputView.printCardsAndResult(dealer, players);
        PlayerResults playerResults = blackJackGame.findPlayerResult(dealer, players);
        OutputView.printFinalGameResult(playerResults);
    }

    private Players createPlayers() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(players);
    }

    private Deck createDeck() {
        ShuffledCardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();
        return Deck.from(shuffledCardsGenerator.generate());
    }

    private void handOutCard(final BlackJackGame blackJackGame, final Dealer dealer, final Players players) {
        askPlayersHit(blackJackGame, players);
        askDealerHit(blackJackGame, dealer);
    }

    private void askPlayersHit(final BlackJackGame blackJackGame, final Players players) {
        for (Player player : players.getPlayers()) {
            askSelection(blackJackGame, player);
        }
    }

    private void askSelection(final BlackJackGame blackJackGame, final Player player) {
        while (!player.isBust() && isRetry(blackJackGame, player)) {
            OutputView.printAllCards(player);
        }
    }

    private boolean isRetry(final BlackJackGame blackJackGame, final Player player) {
        if (!InputView.readSelectionOf(player)) {
            return false;
        }
        return tryGiveCard(blackJackGame, player);
    }

    private void askDealerHit(final BlackJackGame blackJackGame, final Dealer dealer) {
        OutputView.printNewLine();
        while (tryGiveCard(blackJackGame, dealer)) {
            OutputView.printDealerHit(dealer);
        }
        OutputView.printNewLine();
    }

    private boolean tryGiveCard(final BlackJackGame blackJackGame, final Gamer gamer) {
        try {
            blackJackGame.giveCard(gamer);
            return true;
        } catch (CardReceiveException exception) {
            return false;
        }
    }
}
