package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.ShuffleStrategy;
import blackjack.domain.user.User;

public class BlackjackGame {

    private static final int INIT_DRAW_COUNT = 2;

    private final CardPack cardPack;

    BlackjackGame(final CardPack cardPack) {
        this.cardPack = cardPack;
    }

    public BlackjackGame(final CardPack cardPack, final ShuffleStrategy strategy) {
        this(cardPack);
        initCardPack(strategy);
    }

    private void initCardPack(final ShuffleStrategy strategy) {
        this.cardPack.shuffle(strategy);
    }

    public void initDraw(User user) {
        for (int currentCount = 0; currentCount < INIT_DRAW_COUNT; currentCount++) {
            draw(user);
        }
    }

    public void draw(User user) {
        user.drawCard(cardPack);
    }
}
