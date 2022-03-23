package blackjack.domain.user;

public class Player extends User {

    private static final int TWENTY_ONE = 21;

    public Player(UserName playerName) {
        super(playerName);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < TWENTY_ONE;
    }
}
