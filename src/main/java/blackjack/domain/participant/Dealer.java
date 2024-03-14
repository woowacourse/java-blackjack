package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.money.Profit;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int DRAWABLE_MAX_SCORE = 16;
    private static final int START_CARD_SIZE = 1;

    public Dealer() {
        super(Collections.emptyList());
    }

    Dealer(List<Card> cards) {
        super(cards);
    }

    public Profit calculateDealerProfit(Players players) {
        Profit profit = Profit.ZERO;
        for (Player player : players.getPlayers()) {
            Profit dealerProfit = player.calculateProfit(this).reverse();
            profit = profit.add(dealerProfit);
        }
        return profit;
    }

    public Map<Player, Profit> calculatePlayersProfit(Players players) {
        Map<Player, Profit> totalResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            totalResult.put(player, player.calculateProfit(this));
        }
        return totalResult;
    }

    @Override
    protected int getMaxDrawableScore() {
        return DRAWABLE_MAX_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }
}
