package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.handrank.SingleMatchResult;
import blackjack.domain.money.BetAmount;
import blackjack.domain.money.Profit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private static final int START_CARD_SIZE = 2;

    private final Name name;
    private final BetAmount betAmount;

    Player(List<Card> cards, Name name, BetAmount betAmount) {
        super(cards);
        this.name = Objects.requireNonNull(name);
        this.betAmount = Objects.requireNonNull(betAmount);
    }

    Player(List<Card> cards, Name name) {
        this(cards, name, new BetAmount(1));
    }

    public static Player from(String name) {
        return new Player(Collections.emptyList(), new Name(name));
    }

    public static Player from(String name, int money) {
        return new Player(Collections.emptyList(), new Name(name), new BetAmount(money));
    }

    public boolean isWin(Dealer dealer) {
        return !dealer.isWin(this);
    }

    public Profit match(Dealer dealer) {
        SingleMatchResult result = dealer.getHandRank().matchAtDealer(this.getHandRank());
        return result.calculatePlayerProfit(betAmount);
    }

    public boolean isEqualName(Player player) {
        return name.equals(player.name);
    }

    @Override
    protected int getMaxDrawableScore() {
        return BLACKJACK_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }

    public String getName() {
        return name.getName();
    }
}
