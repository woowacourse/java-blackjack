package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DealerResult;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void play() {
        CardDeck entireCardDeck = new CardDeck();
        Dealer dealer = new Dealer(entireCardDeck.generateInitialUserDeck());
        Players players = new Players(entireCardDeck, InputView.requestPlayers());
        OutputView.showInitiate(dealer, players);
        processPlayers(entireCardDeck, players);
        processDealer(entireCardDeck, dealer);
        terminateGame(dealer, players);
    }

    private void terminateGame(Dealer dealer, Players players) {
        DealerResult dealerResult = new DealerResult(dealer, players.getPlayers());
        OutputView.showScoreResult(dealer, players);
        OutputView.showDealerTable(dealerResult);
        OutputView.showIndividualTable(dealer, players);
    }

    private void processPlayers(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            playerDraw(cardDeck, player);
        }
    }

    private void playerDraw(CardDeck cardDeck, Player player) {
        while (player.isAvailableDraw() && isYes(player)) {
            player.draw(cardDeck.draw());
            OutputView.showPlayerCard(player);
        }
    }

    private boolean isYes(Player player) {
        return InputView.requestMoreDraw(player.getName());
    }

    private void processDealer(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isAvailableDraw()) {
            dealer.draw(cardDeck.draw());
            OutputView.showDealerDraw();
        }
    }
}
