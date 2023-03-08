package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.MasterShuffleStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

public class BlackjackGame {

    private static final int INIT_DRAW_COUNT = 2;

    private final CardPack cardPack;

    public BlackjackGame() {
        this.cardPack = new CardPack();
        initCardPack();
    }

    BlackjackGame(final CardPack cardPack) {
        this.cardPack = cardPack;
    }

    private void initCardPack() {
        cardPack.shuffle(new MasterShuffleStrategy());
    }

    public void initDraw(final Dealer dealer, final Players players) {
        for (int currentCount = 0; currentCount < INIT_DRAW_COUNT; currentCount++) {
            dealer.drawCard(cardPack);
            playersDraw(players);
        }
    }

    private void playersDraw(final Players players) {
        for (final Player player : players.getPlayers()) {
            player.drawCard(cardPack);
        }
    }

    public void playerDraw(final Player player) {
        player.drawCard(cardPack);
    }

    public void dealerDraw(final Dealer dealer) {
        dealer.drawCard(cardPack);
    }
}
