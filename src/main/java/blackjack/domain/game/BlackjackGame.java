package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.ShuffleStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BlackjackGame {

    private static final int INIT_DRAW_COUNT = 2;

    private final CardPack cardPack;
    private final Bets bets;

    public BlackjackGame(final ShuffleStrategy strategy) {
        this.cardPack = new CardPack();
        this.bets = new Bets();
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

    public void addBetting(final Player player, final Money money) {
        bets.betting(player, money);
    }

    public Map<User, Money> getUserProfit(final Players players, final Dealer dealer) {
        final Map<User, Money> profitResult = new LinkedHashMap<>();
        final Map<User, Result> result = dealer.getResult(players);
        if (!dealer.isBust()) {
            bets.updatePrizes(result);
        }
        profitResult.put(new Dealer(), bets.getDealerProfit());
        profitResult.putAll(bets.getBets());

        return profitResult;
    }
}
