package controller;

import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import view.InputView;
import view.OutputView;

public class ProgressController {

    private final InputView inputView;
    private final OutputView outputView;

    public ProgressController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(final Dealer dealer, final Players players, final Deck deck) {
        hit(dealer, players, deck);

    }

    private void hit(final Dealer dealer, final Players players, final Deck deck) {
        hitPlayers(players, deck);
        hitDealer(dealer, deck);
    }

    private void hitPlayers(final Players players, final Deck deck) {
        for (Player player : players.getPlayers()) {
            hitPlayer(player, deck);
        }
    }

    private void hitPlayer(final Player player, final Deck deck) {
        boolean isHitAgain = true;
        String name = player.getName();

        while (!player.isEqualOrLargerThanBlackJackNumber() && isHitAgain) {
            outputView.printHitAgain(name);
            isHitAgain = isPlayerHitAgain(player, deck);
            outputView.printPlayerCard(player);
        }
    }

    private boolean isPlayerHitAgain(final Player player, final Deck deck) {
        boolean isHitAgain = inputView.readAnswer();
        if (isHitAgain) {
            player.hit(deck.popCard());
        }
        return isHitAgain;
    }

    private void hitDealer(final Dealer dealer, final Deck deck) {
        while (dealer.isDealerHit()) {
            outputView.printDealerHitAgain();
            dealer.hit(deck.popCard());
        }
    }
}
