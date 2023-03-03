package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.MasterShuffleStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

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
        this.cardPack.shuffle(new MasterShuffleStrategy());
    }

    public void initGame(User user) {
        for (int currentCount = 0; currentCount < INIT_DRAW_COUNT; currentCount++) {
            draw(user);
        }
    }

    public void draw(User user) {
        user.drawCard(cardPack);
    }

    public boolean isDealerEnd(Dealer dealer) {
        final int dealerScore = ScoreReferee.calculateScore(dealer.showCards());
        return (dealerScore > MIN_DEALER_SCORE) || (dealerScore == BUST_SCORE);
    }
}
