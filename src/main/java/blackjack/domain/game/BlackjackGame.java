package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.MasterShuffleStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

public class BlackjackGame {

    private static final int INIT_DRAW_COUNT = 2;
    private static final int MIN_DEALER_SCORE = 16;
    private static final int BUST_SCORE = -1;

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

    public void initDraw(Dealer dealer, Players players) {
        for (int currentCount = 0; currentCount < INIT_DRAW_COUNT; currentCount++) {
            dealer.drawCard(cardPack);
            playersDraw(players);
        }
    }

    private void playersDraw(final Players players) {
        for (Player player : players.getPlayers()) {
            player.drawCard(cardPack);
        }
    }

    public void playerDraw(Player player) {
        player.drawCard(cardPack);
    }

    public void dealerDraw(Dealer dealer) {
        dealer.drawCard(cardPack);
    }

    public boolean isEnd(int score) {
        return (score > MIN_DEALER_SCORE) || isBust(score);
    }

    public boolean isBust(final int score) {
        return score == BUST_SCORE;
    }
}
