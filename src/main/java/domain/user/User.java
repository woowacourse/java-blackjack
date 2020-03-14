package domain.user;

public class User extends Player {
    public User(final String name) {
        super(name);
    }

    public boolean isWin(final Player opponentPlayer) {
        boolean hasMoreOrEqualScore = (getScore() >= opponentPlayer.getScore());

        return isNotBust() && hasMoreOrEqualScore;
    }
}
