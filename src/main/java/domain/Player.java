package domain;

import java.util.Objects;

public class Player extends Participant {
    private static final int DRAWABLE_SCORE_UPPER_BOUND_EXCLUSIVE = 21;

    private final Name name;
    private HitOrStand hitOrStand;
    private int bettingAmount;


    public Player(final String inputName, final int bettingAmount) {
        this.name = new Name(inputName);
        this.hitOrStand = HitOrStand.HIT;
        this.bettingAmount = bettingAmount;
    }

    Player(final String inputName) {
        this(inputName, 0);
    }

    public void bet(final int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public boolean isDrawable() {
        return hitOrStand == HitOrStand.HIT &&
                hand.calculateScore() < DRAWABLE_SCORE_UPPER_BOUND_EXCLUSIVE;
    }

    public void stand() {
        hitOrStand = HitOrStand.STAND;
    }

    public Integer computeEarning(final Hand dealerHand) {
        return PlayerOutcome.of(hand, dealerHand)
                            .calculateEarning(bettingAmount);
    }

    @Override
    public String name() {
        return name.value();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
