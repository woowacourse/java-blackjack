package domain.participant;

import java.math.BigDecimal;

public final class Player extends Participant {

    private final Money money;

    Player(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateScore() < MAX_SCORE;
    }

    public BigDecimal multiply(PlayerResult playerResult) {
        BigDecimal profitRate = BigDecimal.valueOf(playerResult.getProfitRate());

        return money.getValue().multiply(profitRate);
    }
}
