package blackjack.domain.user;

import java.util.function.Predicate;

public class Player extends User {

    private final Predicate<UserName> hitDesireChecker;

    public Player(final UserName userName, final Predicate<UserName> hitDesireChecker) {
        super(userName);
        this.hitDesireChecker = hitDesireChecker;
    }

    @Override
    protected boolean wantToHit() {
        return isNotFinished() && hitDesireChecker.test(getUserName());
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
