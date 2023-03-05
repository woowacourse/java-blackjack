package blackjack.domain.player;

public class Dealer extends User {

    private static final int MINIMUM_SCORE = 17;

    public Dealer() {
        super(new Name("딜러"));
    }

    public String getDealerName() {
        return name.getName();
    }

    @Override
    public boolean isUnderLimit() {
        return playerCards.getTotalScore() < MINIMUM_SCORE;
    }
}
