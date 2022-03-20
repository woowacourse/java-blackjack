package domain.participant;

import java.math.BigDecimal;

public class Player extends Participant {

    private final Money money;

    Player(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateScore() < MAX_SCORE;
    }

    public int multiply(PlayerResult playerResult) {
        BigDecimal profitRate = BigDecimal.valueOf(playerResult.getProfitRate());
        BigDecimal result = money.getValue().multiply(profitRate);

        return result.intValue();
    }
}
