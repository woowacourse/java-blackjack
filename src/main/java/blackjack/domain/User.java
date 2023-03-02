package blackjack.domain;

import blackjack.domain.player.PlayerCards;

public class User {
    Name name;
    PlayerCards playerCards;

    public User(Name name, PlayerCards playerCards) {
        this.name = name;
        this.playerCards = playerCards;
    }
}
