package blackjack.dto;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Money;
import blackjack.domain.player.Player;

public class ChallengerMoneyDto {

    private final Challenger challenger;
    private final Money money;

    public ChallengerMoneyDto(Player challenger, Money money) {
        this.challenger = (Challenger) challenger;
        this.money = money;
    }

    public Challenger getChallenger() {
        return challenger;
    }

    public Money getMoney() {
        return money;
    }
}
