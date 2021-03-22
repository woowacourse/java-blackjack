package blackjack.controller.dto;

import blackjack.domain.Money;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class PlayerRequestDto {

    private Name name;
    private Money money;

    public PlayerRequestDto(Name name, Money money) {
        this.name = name;
        this.money = money;
    }

    public Name getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    public Player toEntity() {
        return new Player(name, money);
    }
}
