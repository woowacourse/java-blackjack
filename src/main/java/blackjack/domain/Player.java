package blackjack.domain;

import java.util.Objects;

public class Player extends Participant {
    private final Name name;
    private final BetAmount betAmount;

    private Player(Name name, Hand hand, BetAmount betAmount) {
        super(hand);
        validate(name, betAmount);
        this.name = name;
        this.betAmount = betAmount;
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init(), BetAmount.of(1));
    }

    public Player withBetAmount(BetAmount betAmount) {
        return new Player(name, hand, betAmount);
    }

    private void validate(Name name, BetAmount betAmount) {
        validateNameAndBetAmountNotNull(name, betAmount);
    }

    private void validateNameAndBetAmountNotNull(Name name, BetAmount betAmount) {
        Objects.requireNonNull(name, "name 은 null 이 올 수 없습니다.");
        Objects.requireNonNull(betAmount, "betAmount 은 null 이 올 수 없습니다.");
    }

    public String name() {
        return name.getName();
    }

    public boolean canHit() {
        return score() <= BLACKJACK_THRESHOLD;
    }
}