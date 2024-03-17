package blackjack.model.results;

import blackjack.model.participants.Player;
import blackjack.vo.Money;
import java.util.Map;

public class PlayerProfits {
    private final Map<Player, Money> profits;

    public PlayerProfits(Map<Player, Money> profits) {
        this.profits = profits;
    }

    public Map<Player, Money> getProfits() {
        return profits;
    }
}
