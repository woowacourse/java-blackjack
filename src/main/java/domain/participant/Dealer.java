package domain.participant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import domain.betting.BettingMoney;
import util.Constants;

public class Dealer extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;

    private final Map<Player, BettingMoney> bettingMoneyByPlayer;

    public Dealer(HandCards handCards) {
        super(new Name(Constants.DEALER_NAME), handCards);
        this.bettingMoneyByPlayer = new HashMap<>();
    }

    public boolean checkCardsCondition() {
        return getOptimalCardValueSum() < LIMIT_TAKE_CARD_VALUE;
    }

    public void savePlayerBettingMoney(Player player, BettingMoney bettingMoney) {
        bettingMoneyByPlayer.put(player, bettingMoney);
    }

    public Map<Player, BettingMoney> getBettingMoneyByPlayer() {
        return Map.copyOf(bettingMoneyByPlayer);
    }
}
