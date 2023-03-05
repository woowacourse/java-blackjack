package blackjack.domain.player;

public class Dealer extends User {

    private static final int MINIMUM_SCORE = 17;

    private final Name name;

    public Dealer() {
        this.name = new Name("딜러");
    }

    public String getDealerName() {
        return name.getName();
    }

    @Override
    public boolean isUnderLimit() {
        return playerCards.getTotalScore() < MINIMUM_SCORE;
    }
}
