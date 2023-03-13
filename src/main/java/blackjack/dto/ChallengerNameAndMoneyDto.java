package blackjack.dto;

import blackjack.domain.player.ChallengerName;
import blackjack.domain.player.Money;

public class ChallengerNameAndMoneyDto {

    private final ChallengerName name;
    private final Money money;

    public ChallengerNameAndMoneyDto(final ChallengerName name, final Money money) {
        this.name = name;
        this.money = money;
    }

    public ChallengerName getChallengerName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }
}
