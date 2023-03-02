package blackjack.domain;

import blackjack.domain.player.Name;
import blackjack.domain.player.PlayerCards;

public class Player extends User {

    public Player(Name name, PlayerCards playerCards) {
        super(name, playerCards);
    }
}
