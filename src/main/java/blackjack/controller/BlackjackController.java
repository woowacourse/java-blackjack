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

    public void startGame() {
        List<String> playerNames = InputView.getPlayerNames();
        Dealer dealer = new Dealer();
        Players players = new Players(playerNames);
        Blackjack blackjack = Blackjack.of(RandomNumberGenerator.getInstance(), dealer, players);

        OutputView.printInitStatus(dealer, players.getPlayers());

        progressGame(blackjack, players, dealer);
    }

    public void progressGame(Blackjack blackjack, Players players, Dealer dealer) {
        List<Player> p = players.getPlayers();
        for (Player player : p) {
            askDealCardToPlayer(blackjack, player);
        }

        askDealCardToDealer(blackjack, dealer);

        endGame(blackjack, dealer ,players);
    }

    private void askDealCardToPlayer(Blackjack blackjack, Player player) {
        if (player.score().isBlackjack()) {
            OutputView.printBlackjack(player);
            return;
        }

        while (!player.score().isBust() && InputView.askAdditionalCard(player)) {
            blackjack.dealAdditionalCardToPlayer(RandomNumberGenerator.getInstance(), player);
            OutputView.printCards(player);
        }
    }

    private void askDealCardToDealer(Blackjack blackjack, Dealer dealer) {
        if (dealer.score().isBlackjack()) {
            OutputView.printBlackjack(dealer);
            return;
        }

        if (dealer.score().isHit()) {
            blackjack.dealAdditionalCardToDealer(RandomNumberGenerator.getInstance(), dealer);
            OutputView.printDealerAdditionalCard();
        }
    }

    private void endGame(Blackjack blackjack, Dealer dealer, Players players) {
        OutputView.printCardsAndResult(dealer, players.getPlayers());
        OutputView.printResult(blackjack.result(dealer, players));
    }
}
