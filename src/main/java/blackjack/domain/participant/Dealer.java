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

    public boolean isWin(Player player) {
        if (player.isBusted() || this.isBlackjack()) {
            return true;
        }
        if (this.isBusted() || player.isBlackjack()) {
            return false;
        }
        return this.calculateScore() >= player.calculateScore();
    }

    private boolean isLose(Player player) {
        return !isWin(player);
    }

    public Map<Player, Profit> calculatePlayersProfit(Players players) {
        Map<Player, Profit> totalResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            totalResult.put(player, player.match(this));
        }
        return totalResult;
    }

    public int calculateWinCount(Players players) {
        return (int) players.getPlayers().stream()
                .filter(this::isWin)
                .count();
    }

    public int calculateLoseCount(Players players) {
        return (int) players.getPlayers().stream()
                .filter(this::isLose)
                .count();
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
