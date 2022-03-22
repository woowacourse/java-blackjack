package blackjack.domain.result;

import java.util.Map;

import blackjack.domain.gamer.role.Role;

public class ProfitResult {
    private final Map<Role, Integer> profits;

    public ProfitResult(Map<Role, Integer> profits) {
        this.profits = profits;
    }

    public Map<Role, Integer> get() {
        return profits;
    }
}
