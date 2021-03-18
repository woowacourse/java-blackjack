package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Names;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;

public class BlackJackController {

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.generateUserDeck());

        Names names = new Names(InputView.requestPlayers());
        Players players = new Players(cardDeck, names, InputView.requestMoney(names));
        OutputView.showInitiate(dealer, players);

        processPlayers(cardDeck, players);
        processDealer(cardDeck, dealer);

        endBlackjack(dealer, players);
    }

    private void processPlayers(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            playerDraw(cardDeck, player);
        }
    }

    private void playerDraw(CardDeck cardDeck, Player player) {
        if (!player.isFinished()) {
            String input = InputView.requestMoreDraw(player.getName());
            if (player.wantToDraw(input)) {
                player.draw(cardDeck);
                OutputView.showPlayerCard(player);
                playerDraw(cardDeck, player);
            }
        }
    }

    private void processDealer(CardDeck cardDeck, Dealer dealer) {
        if (!dealer.isFinished()) {
            dealer.draw(cardDeck);
            OutputView.showDealerDraw();
            processDealer(cardDeck, dealer);
        }
    }

    private void endBlackjack(Dealer dealer, Players players) {
        OutputView.showScoreResult(dealer, players);

        Map<Player, Double> playerEarning = BlackjackGame.playerEarningResult(dealer, players);
        double dealerEarning = BlackjackGame.getDealerEarning(playerEarning);

        OutputView.showEarning(playerEarning);
        OutputView.showEarning(dealerEarning * -1);
    }
}
