package blackjack.dto;

import blackjack.model.money.Money;

public record PlayerResultDto(String name, Money profit) {
}
