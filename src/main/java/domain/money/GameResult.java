package domain.money;

import java.util.function.BiPredicate;

public enum GameResult {
    WIN((current, opponent) -> current > opponent, 1),
    LOSE((current, opponent) -> current < opponent, -1),
    DRAW(Integer::equals, 0);

    private final BiPredicate<Integer, Integer> condition;

    private final double benefitRatio;

    GameResult(BiPredicate<Integer, Integer> condition, double benefitRatio) {
        this.condition = condition;
        this.benefitRatio = benefitRatio;
    }

    public BiPredicate<Integer, Integer> getCondition() {
        return condition;
    }

    public double getBenefitRatio() {
        return benefitRatio;
    }
}
