package blackjack.domain;

import blackjack.domain.player.Name;
import blackjack.domain.player.PlayerCards;

public class Dealer extends User {

    public Dealer(Name name, PlayerCards playerCards) {
        super(name, playerCards);
    }

    public boolean isNotOverSeventeen() {
        return playerCards.getTotalScore() < 17;
    }
}
