package blackjack.domain;

public class Dealer extends User {

    public static final String DEALER = "딜러";

    public Dealer(UserCards userCards) {
        super(DEALER, userCards);
    }
}
