package domain.player;

public class User extends Player {
    public User(final String name) {
        super(name);
    }

    public boolean isWin(final Player opponentPlayer) {
        boolean dealerBust = opponentPlayer.isBust();
        boolean hasMoreOrEqualScore = (getScore() >= opponentPlayer.getScore());

        return isNotBust() && (dealerBust || hasMoreOrEqualScore);
    }
}
