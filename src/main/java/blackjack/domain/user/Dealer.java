package blackjack.domain.user;

import blackjack.domain.user.name.DealerName;

public class Dealer extends User {

    public Dealer() {
        super(new DealerName());
    }
}
