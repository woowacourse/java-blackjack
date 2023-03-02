package blackjack.domain;

import blackjack.domain.player.PlayerCards;

public class Dealer extends User {

    public Dealer(Name name, PlayerCards playerCards) {
        super(name, playerCards);
    }
}
