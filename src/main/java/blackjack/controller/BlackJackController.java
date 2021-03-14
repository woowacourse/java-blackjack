package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Names;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.generateUserDeck());

        Names names = new Names(InputView.requestPlayers());
        Players players = new Players(cardDeck, names, InputView.requestMoney(names));
        OutputView.showInitiate(dealer, players);

        processPlayers(cardDeck, players);
        processDealer(cardDeck, dealer);

        endBlackJack(dealer, players);
    }

    private void processPlayers(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            playerDraw(cardDeck, player);
        }
    }

    private void playerDraw(CardDeck cardDeck, Player player) {
        String input = InputView.requestMoreDraw(player.getName());
        if (player.isAvailableDraw() && player.isDrawable(input)) {
            player.draw(cardDeck.draw());
            OutputView.showPlayerCard(player);
            playerDraw(cardDeck, player);
        }
    }

    private void processDealer(CardDeck cardDeck, Dealer dealer) {
        if (dealer.isAvailableDraw()) {
            dealer.draw(cardDeck.draw());
            OutputView.showDealerDraw();
            processDealer(cardDeck, dealer);
        }
    }

    private void endBlackJack(Dealer dealer, Players players) {
        OutputView.showScoreResult(dealer, players);
        double dealerEarning = 0;
        for (Player player : players.getPlayers()) {
            BlackjackResult result = Result.getResult(player, dealer);
            double earning = player.getMoney(result.getEarningRate());
            dealerEarning += earning;
            OutputView.showEarning(player.getName(), earning);
        }
        OutputView.showEarning(dealerEarning * -1);
    }
}
