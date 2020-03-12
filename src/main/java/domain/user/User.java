package domain.user;

public class User extends Player {
    public User(final String name) {
        super(name);
    }

    public boolean isWin(final Player opponentPlayer) {
        return isNotBust() && (opponentPlayer.isBust() || getScore() >= opponentPlayer.getScore());
    }
}
