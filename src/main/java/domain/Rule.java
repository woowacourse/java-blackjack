package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.function.BiPredicate;

public class Rule {

    private final BiPredicate<Player, Dealer> condition;
    private final FinalResult finalResult;

    public Rule(final BiPredicate<Player, Dealer> condition, final FinalResult finalResult) {
        this.condition = condition;
        this.finalResult = finalResult;
    }

    public boolean matches(final Player player, final Dealer dealer) {
        return condition.test(player, dealer);
    }

    public FinalResult getFinalResult() {
        return finalResult;
    }
}
