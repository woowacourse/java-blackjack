package blackjack.domain.result;

import java.util.List;

public class PlayerBets {
    private final List<PlayerBet> values;

    public PlayerBets(List<PlayerBet> values) {
        this.values = values;
    }

    public List<PlayerBet> getValues() {
        return values;
    }
}
