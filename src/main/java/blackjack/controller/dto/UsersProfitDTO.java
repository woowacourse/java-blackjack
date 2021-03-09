package blackjack.controller.dto;

import blackjack.domain.player.Name;
import java.util.Map;

public class UsersProfitDTO {
    private final Map<Name, Integer> profits;

    public UsersProfitDTO(Map<Name, Integer> profits) {
        this.profits = profits;
    }

    public Map<Name, Integer> getProfits() {
        return profits;
    }
}
