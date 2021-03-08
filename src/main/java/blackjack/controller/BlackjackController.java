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
        Players players = new Players(InputView.getPlayerNames());

        initGame(dealer, players);
        playBlackjack(dealer, players);

        OutputView.printHandResult(players.toList(), dealer);
        GameResultDto gameResultDto = BlackjackManager.getGameResult(dealer, players);
        OutputView.printGameResult(gameResultDto);
    }

    private void initGame(Dealer dealer, Players players) {
        BlackjackManager.initGame(players, dealer);
        OutputView.printInitGame(players.toList(), dealer);
    }

    private void playBlackjack(Dealer dealer, Players players) {
        for (Player player : players.toList()) {
            playHit(player, dealer);
        }
        while (!dealer.isOverLimitScore()) {
            dealer.receiveCard(dealer.drawCard());
            OutputView.printDealerHit();
        }
    }

    private void playHit(Player player, Dealer dealer) {
        while (!player.isOverLimitScore()) {
            UserAnswer userAnswer = UserAnswer.getUserAnswer(InputView.getHitOrStay(player.getName()));
            if (userAnswer.isStay()) {
                OutputView.printCards(player);
                break;
            }
            player.receiveCard(dealer.drawCard());
            OutputView.printCards(player);
        }
        if (player.isOverLimitScore()) {
            OutputView.printPlayerBurst(player.getName());
        }
    }
}
