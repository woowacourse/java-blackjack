package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Deck;
import blackjackgame.view.OutputView;

public class BlackjackGame {
    private static final int EXECUTION_COUNT = 2;

    private final CardHolderGamer dealer;
    private final Gamers players;

    public BlackjackGame(CardHolderGamer dealer, Gamers players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck);
        players.drawNTimes(deck, EXECUTION_COUNT);
    }

    private void dealerDraw(Deck deck) {
        dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer), EXECUTION_COUNT);
    }

    private void playerDraw(Deck deck, CardHolderGamer player) {
        player.draw(deck, new PlayerRandomCardDrawStrategy(player), EXECUTION_COUNT);
    }

    public boolean isPlayerDead(CardHolderGamer player) {
        return player.isDead();
    }

    public void dealerTryDraw(Deck deck) {
        try {
            dealerDraw(deck);
            OutputView.printDealerAdditionalCardMessage();
        } catch (IllegalStateException e) {
            OutputView.printDealerNoAdditionalCardMessage();
        }
    }

    public void playerTryDraw(Deck deck, CardHolderGamer player) {
        try {
            playerDraw(deck, player);
        } catch (IllegalStateException e) {
            OutputView.printPlayerSummationOverDeadPoint();
        }
    }

    public Gamers getPlayers() {
        return players;
    }
}
