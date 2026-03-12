package blackjack.domain;

public class DealerName implements Name {

    public static final String DEALER_NICKNAME = "딜러";

    @Override
    public String toString() {
        return DEALER_NICKNAME;
    }
}
