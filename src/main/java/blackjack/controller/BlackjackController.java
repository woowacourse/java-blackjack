package blackjack.controller;

import blackjack.domain.card.CardManager;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        final CardManager cardManager = CardManager.create();
        final Gamers gamers = cardManager.initiateGamers(InputView.receiveNames());
        OutputView.gameStart(gamers);

        for (Gamer gamer : gamers.players()) {
            hitOrStand(cardManager, gamer);
        }
        dealerHitOrStand(cardManager, gamers);
        printResult(gamers);
    }

    private void hitOrStand(CardManager cardManager, Gamer gamer) {
        while (InputView.receiveAnswer(gamer.getName())) {
            gamer.receiveCard(cardManager.giveCard());
            OutputView.allCards(gamer);
        }
    }

    private void dealerHitOrStand(CardManager cardManager, Gamers gamers) {
        if (gamers.dealer().checkBoundary()) {
            gamers.dealer().receiveCard(cardManager.giveCard());
            OutputView.dealerHit();
        }
    }

    private void printResult(Gamers gamers) {
        OutputView.gamersAllCards(gamers);
        OutputView.printResultTitle();
        OutputView.dealerResult(gamers.resultWithCount());
        OutputView.playersResult(gamers.resultWithName());
    }
}
