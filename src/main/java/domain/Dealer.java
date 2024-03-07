
package domain;

import java.util.List;

public class Dealer extends Gamer {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }
}
