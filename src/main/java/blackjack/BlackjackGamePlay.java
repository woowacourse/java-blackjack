package blackjack;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.GameBettingManager;
import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.deck.PlayingDeck;
import blackjack.domain.deck.shuffle.RandomShuffle;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackjackGamePlay {

    private final GameBettingManager gameBettingManager = new GameBettingManager();

    public void run() {
        List<Player> players = retryUntilSuccess(this::registerPlayer);
        registerPlayersBatting(players);

        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new RandomShuffle());

        playBlackJack(new Dealer(playingDeck), players);
    }

    private List<Player> registerPlayer() {
        List<String> playersName = InputView.readPlayersName();
        return playersName.stream()
                .map(Player::new)
                .toList();
    }

    private void registerPlayersBatting(List<Player> players) {
        for (Player player : players) {
            Betting bettingMoney = retryUntilSuccess(() -> new Betting(InputView.askPlayerForBatting(player)));
            gameBettingManager.registerPlayerBetting(player, bettingMoney);
        }
    }

    private void playBlackJack(Dealer dealer, List<Player> players) {
        initialDraw(dealer, players);
        runPlayerTurn(dealer, players);
        runDealerTurn(dealer);
        calculateResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        dealer.initialDraw();
        players.forEach(player -> player.initialDraw(dealer));

        OutputView.printInitialDrawResult(dealer, players);
    }

    private void runPlayerTurn(Dealer dealer, List<Player> players) {
        players.forEach(player -> hitOrStand(dealer, player));
    }

    private void hitOrStand(Dealer dealer, Player player) {
        while (player.canHit() && retryUntilSuccess(() -> InputView.askPlayerForCard(player))) {
            player.receiveCard(dealer.drawCard());
            OutputView.printPlayerCard(player);
        }

        if (player.canHit()) {
            OutputView.printPlayerCard(player);
        }
    }

    private void runDealerTurn(Dealer dealer) {
        if (dealer.canHit()) {
            dealer.receiveCard(dealer.drawCard());
            OutputView.printDealerHit();
        }
    }

    private void calculateResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            gameBettingManager.calculatePlayerProfit(dealer, player);
        }

        OutputView.printCardScore(dealer, players);
        OutputView.printResult(gameBettingManager);
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return retryUntilSuccess(supplier);
        }
    }
}
