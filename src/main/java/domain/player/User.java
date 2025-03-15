package domain.player;

import domain.Bet;
import domain.card.Cards;
import domain.card.Deck;
import domain.state.Hittable;
import domain.state.State;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class User extends Player {

    private final Bet bet;

    private User(String name, Bet bet, State state) {
        super(name, state);
        this.bet = bet;
    }

    public static User of(String name, int bet) {
        return new User(name, new Bet(bet), Hittable.initialUserState());
    }

    @Override
    public void openInitialCards() {
        openCards(2);
    }

    public void hitUntilStay(Deck deck,
                             Function<User, Boolean> wantHit, BiConsumer<User, Cards> onHit) {
        while (!isFinished() && wantHit.apply(this)) {
            hit(deck);
            onHit.accept(this, cards());
        }
        if (!isFinished()) {
            stay();
        }
    }
}
