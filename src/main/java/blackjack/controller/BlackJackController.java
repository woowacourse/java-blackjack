package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.DealerResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Names;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import jdk.internal.util.xml.impl.Input;

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
        if (player.isAvailableDraw() && player.isYes(input)) {
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
        DealerResult dealerResult = new DealerResult(dealer, players.getPlayers());
        OutputView.showDealerTable(dealerResult);
        OutputView.showPlayerTable(dealer, players);
    }
}
