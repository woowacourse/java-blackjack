package domain.user;

public class User extends Player {
    public User(final String name) {
        super(name);
    }

    public boolean isWin(final Player opponentPlayer) {
        boolean dealerBust = opponentPlayer.isBust();
        boolean userWin = isNotBust() && (opponentPlayer.isBust() || getScore() >= opponentPlayer.getScore());

        return dealerBust || userWin;
    }
}
