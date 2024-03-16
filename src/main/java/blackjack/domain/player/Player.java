package blackjack.domain.player;

import java.util.function.Predicate;

public class Player extends User {

    private final Predicate<UserName> userWantToHit;

    public Player(final UserName userName, final Predicate<UserName> userWantToHit) {
        super(userName);
        this.userWantToHit = userWantToHit;
    }

    public static Player of(final UserName userName, final Predicate<UserName> userWantToHit) {
        return new Player(userName, userWantToHit);
    }

    @Override
    protected boolean wantToHit() {
        return isNotFinished() && userWantToHit.test(getUserName());
    }
}
