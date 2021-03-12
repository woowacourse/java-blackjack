package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.money.Money;

public abstract class Running extends Started {

    public static final int RULE_TO_DRAW_FOR_DEALER = 16;

    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(final Money money) {
        throw new UnsupportedOperationException("[ERROR] 종료된 상태가 아니므로 이익을 계산할 수 없습니다.");
    }

    @Override
    public boolean satisfyRule() {
        return cards().calculate() <= RULE_TO_DRAW_FOR_DEALER;
    }
}
