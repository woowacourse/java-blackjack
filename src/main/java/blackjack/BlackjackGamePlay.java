package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.deck.PlayingDeck;
import blackjack.domain.betting.Betting;
import blackjack.domain.deck.shuffle.RandomShuffle;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.betting.GameBettingManager;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackjackGamePlay {

    private final PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new RandomShuffle());
    private final GameBettingManager gameBettingManager = new GameBettingManager();

    public void run() {
        List<Player> players = retryUntilSuccess(this::registerPlayer);
        registerPlayersBatting(players);
        playBlackJack(new Dealer(), players);
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
        runPlayerTurn(players);
        runDealerTurn(dealer);
        calculateResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        drawCardToDealer(dealer);
        drawCardToPlayer(players);
        OutputView.printInitialDrawResult(dealer, players);
    }

    private void drawCardToDealer(Dealer dealer) {
        Card firstDealerCard = playingDeck.drawCard();
        Card secondDealerCard = playingDeck.drawCard();

        dealer.initialDraw(List.of(firstDealerCard, secondDealerCard));
    }

    private void drawCardToPlayer(List<Player> players) {
        for (Player player : players) {
            Card firstPlayerCard = playingDeck.drawCard();
            Card secondPlayerCard = playingDeck.drawCard();

            player.initialDraw(List.of(firstPlayerCard, secondPlayerCard));
        }
    }

    private void runPlayerTurn(List<Player> players) {
        for (Player player : players) {
            hitOrStand(player);
        }
    }

    private void hitOrStand(Player player) {
        while (player.canHit() && retryUntilSuccess(() -> InputView.askPlayerForCard(player))) {
            executeHit(player);
        }
        if (player.canHit()) {
            OutputView.printPlayerCard(player);
        }
    }

    private void executeHit(Player player) {
        Card card = playingDeck.drawCard();
        player.receiveCard(card);
        OutputView.printPlayerCard(player);
    }

    private void runDealerTurn(Dealer dealer) {
        if (dealer.canHit()) {
            Card card = playingDeck.drawCard();
            dealer.receiveCard(card);
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
