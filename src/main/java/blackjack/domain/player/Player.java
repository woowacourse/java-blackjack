package blackjack.domain.player;

public class Player extends User {

    private static final int SCORE_LIMIT = 21;

    public Player(Name name) {
        super(name);
    }

    public String getPlayerName() {
        return name.getName();
    }

    @Override
    public boolean isUnderLimit() {
        return hand.getTotalScore() < SCORE_LIMIT;
    }
}
