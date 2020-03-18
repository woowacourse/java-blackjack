package domain.user;

import domain.result.PrizeRatio;
import domain.result.RatioRule;

import java.util.Arrays;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    protected boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint();
    }

    public PrizeRatio decideRatio(Dealer dealer) {
        return Arrays.stream(RatioRule.values())
                .filter(ratioRule -> ratioRule.condition(this, dealer))
                .findFirst()
                .orElseThrow(() -> new AssertionError("게임 규칙이 올바르지 않습니다."))
                .getPrizeRatio();
    }
}
