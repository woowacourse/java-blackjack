package blackjack;

import blackjack.domain.result.GameBattingManager;
import blackjack.domain.card.Card;
import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.deck.PlayingDeck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGamePlay {

    private final PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());
    private final GameBattingManager gameBattingManager = new GameBattingManager();

    public void run() {
        List<Player> players = registerPlayer();
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
            gameBattingManager.registerPlayerBatting(player, InputView.askPlayerForBatting(player));
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

        dealer.receiveCard(firstDealerCard);
        dealer.receiveCard(secondDealerCard);
    }

    private void drawCardToPlayer(List<Player> players) {
        for (Player player : players) {
            Card firstPlayerCard = playingDeck.drawCard();
            Card secondPlayerCard = playingDeck.drawCard();

            player.receiveCard(firstPlayerCard);
            player.receiveCard(secondPlayerCard);
        }
    }

    private void runPlayerTurn(List<Player> players) {
        for (Player player : players) {
            hitOrStand(player);
        }
    }

    private void hitOrStand(Player player) {
        while (player.canHit() && InputView.askPlayerForCard(player)) {
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
            gameBattingManager.decideWinner(dealer, player);
        }

        OutputView.printCardScore(dealer, players);
        OutputView.printResult(gameBattingManager);
    }
}
