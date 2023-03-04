package blackjack.domain.player;

public class Player extends User {

    private static final int SCORE_LIMIT = 21;
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name.getName();
    }

    @Override
    public boolean isUnderLimit() {
        return playerCards.getTotalScore() <= SCORE_LIMIT;
    }
}
