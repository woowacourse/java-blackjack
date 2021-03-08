package blackjack.controller;

import blackjack.domain.card.CardManager;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        final CardManager cardManager = CardManager.create();
        final Gamers gamers = cardManager.initiateGamers(InputView.receiveNames());
        final Dealer dealer = new Dealer(cardManager.giveFirstHand());
        OutputView.gameStart(gamers, dealer);

        for (Gamer gamer : gamers.players()) {
            playerHitOrStand(cardManager, gamer);
        }
        dealerHitOrStand(cardManager, dealer);
        printResult(gamers, dealer);
    }

    private void playerHitOrStand(CardManager cardManager, Gamer gamer) {
        while (InputView.receiveAnswer(gamer.getName())) {
            gamer.receiveCard(cardManager.giveCard());
            OutputView.allCards(gamer);
        }
    }

    private void dealerHitOrStand(CardManager cardManager, Dealer dealer) {
        if (dealer.checkBoundary()) {
            dealer.receiveCard(cardManager.giveCard());
            OutputView.dealerHit();
        }
    }

    private void printResult(Gamers gamers, Dealer dealer) {
        OutputView.gamersAllCards(gamers, dealer);
        OutputView.printResultTitle();
        OutputView.dealerResult(gamers.resultWithCount(dealer));
        OutputView.playersResult(gamers.resultWithName(dealer));
    }
}
