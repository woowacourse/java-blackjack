package dto;

import domain.participant.BettingMoney;
import domain.participant.Name;

public record PlayerCreationInfo(
        Name name,
        BettingMoney bettingMoney
) {
}
