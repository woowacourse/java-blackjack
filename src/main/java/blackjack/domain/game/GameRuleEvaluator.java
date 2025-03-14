package blackjack.domain.game;

public class GameRuleEvaluator {

    public static final int BUSTED_STANDARD_VALUE = 21;

    public boolean isBusted(Participant participant) {
        return participant.isOverLimit(BUSTED_STANDARD_VALUE);
    }
}
