package blackjack.controller;

import blackjack.domain.BlackjackManager;
import blackjack.domain.GameResultDto;
import blackjack.domain.UserAnswer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play() {
        Dealer dealer = new Dealer();
        Players players = initPlayers();

        initGame(dealer, players);
        if (!dealer.isBlackjack()) {
            playBlackjack(dealer, players);
        }

        OutputView.printHandResult(players.toList(), dealer);
        GameResultDto gameResultDto = BlackjackManager.getGameResult(dealer, players);
        OutputView.printGameResult(gameResultDto);
    }

    private Players initPlayers() {
        try {
            return new Players(InputView.getPlayerNames());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printException(e);
            return initPlayers();
        }
    }

    private void initGame(Dealer dealer, Players players) {
        BlackjackManager.initGame(players, dealer);
        OutputView.printInitGame(players.toList(), dealer);
    }

    private void playBlackjack(Dealer dealer, Players players) {
        for (Player player : players.toList()) {
            playHit(player, dealer);
        }
        while (!dealer.isFinished()) {
            dealer.receiveCard(dealer.drawCard());
            OutputView.printDealerHit();
        }
    }

    private void playHit(Player player, Dealer dealer) {
        try {
            hitOrStay(player, dealer);
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printException(e);
            playHit(player, dealer);
        }
    }

    private void hitOrStay(Player player, Dealer dealer) {
        if (player.isBlackjack()) {
            OutputView.printPlayerBlackjack(player.getName());
            return;
        }
        UserAnswer userAnswer = UserAnswer.getUserAnswer(InputView.getHitOrStay(player.getName()));
        if (userAnswer.isStay()) {
            player.stay();
            OutputView.printCards(player);
            return;
        }
        player.receiveCard(dealer.drawCard());
        OutputView.printCards(player);
        if (player.isFinished()) {
            OutputView.printPlayerBurst(player.getName());
            return;
        }
        playHit(player, dealer);
    }
}
