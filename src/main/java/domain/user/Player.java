package domain.user;

import java.util.Arrays;

import domain.result.PlayerResultRule;
import domain.result.Ratio;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    protected boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint();
    }

    public Ratio decideRatio(Dealer dealer) {
        return Arrays.stream(PlayerResultRule.values())
                .filter(rule -> rule.condition(this, dealer))
                .findFirst()
                .orElseThrow(() -> new AssertionError("게임 규칙이 올바르지 않습니다."))
                .getRatio();
    }
}
