package blackjack.controller;

import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.strategy.RandomNumberGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    private Blackjack blackjack;
    private Players players;
    private Dealer dealer;

    public void run() {
        startGame();
        progressGame();
        endGame();
    }

    public void startGame() {
        List<String> playerNames = InputView.getPlayerNames();
        dealer = new Dealer();
        players = new Players(playerNames);
        blackjack = Blackjack.of(RandomNumberGenerator.getInstance(), dealer, players);

        OutputView.printInitStatus(dealer, players.getPlayers());
    }

    public void progressGame() {
        List<Player> p = players.getPlayers();
        for (Player player : p) {
            askDealCardToPlayer(player);
        }

        askDealCardToDealer();
    }

    private void askDealCardToPlayer(Player player) {
        if (player.isBlackjack()) {
            OutputView.printBlackjack(player);
            return;
        }

        while (!player.isBust() && InputView.askAdditionalCard(player)) {
            blackjack.dealAdditionalCardToPlayer(RandomNumberGenerator.getInstance(), player);
            OutputView.printCards(player);
        }
    }

    private void askDealCardToDealer() {
        if (dealer.isBlackjack()) {
            OutputView.printBlackjack(dealer);
            return;
        }

        if (dealer.isHit()) {
            blackjack.dealAdditionalCardToDealer(RandomNumberGenerator.getInstance(), dealer);
            OutputView.printDealerAdditionalCard();
        }
    }

    private void endGame() {
        OutputView.printCardsAndResult(dealer, players.getPlayers());
        OutputView.printResult(blackjack.result(dealer, players));
    }
}
