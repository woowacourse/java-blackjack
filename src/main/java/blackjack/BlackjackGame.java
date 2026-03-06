package blackjack;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    private Dealer dealer;
    private Players players;

    public BlackjackGame() {
    }

    public void start() {
        setupGame();
        dealer.dealInitialCardsTo(players);
        printInitialDeal();
        dealer.process(players);
        printResults();
    }

    private void setupGame() {
        dealer = new Dealer(new Deck());
        players = createPlayers();
    }

    private Players createPlayers() {
        final List<String> names = InputView.readPlayerNames();
        final List<Player> playerList = names.stream()
                .map(Player::new)
                .toList();
        return new Players(playerList);
    }

    private void printInitialDeal() {
        OutputView.printInitialDeal(players, dealer);
    }

    private void printResults() {
        OutputView.printFinalCards(players, dealer);
        final GameResults gameResults = GameResults.calculate(players, dealer);
        OutputView.printFinalResults(gameResults);
    }
}
