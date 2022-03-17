package domain.participant;

import java.util.Map;

public class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public int getResultMoney(Map<Player, Result> playersResult) {
        return (int) playersResult.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getMoney() * entry.getValue().getProfitRate() * -1)
            .sum();
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateScore() <= MAX_CARD_SUM;
    }
}
