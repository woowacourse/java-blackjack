package blackjack.domain.user;

public class Dealer extends User {
    public static final String DEALER_NAME = "딜러";
    public static final int INITIAL_COUNT = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isMustHit() {
        return state.cards()
                .totalScore()
                .isDealerMustToHitScore();
    }

    public Cards showOneCard() {
        return new Cards(state.cards()
                .getOneCard());
    }

    @Override
    public Cards showInitialCard() {
        return state.cards()
                .getCardsByCount(INITIAL_COUNT);
    }
}
