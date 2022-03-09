package blackjack.domain.result;

import blackjack.domain.gamer.Name;

public class PlayerResult {
    private final Name name;
    private final BlackJackResult result;

    public PlayerResult(Name name, BlackJackResult result) {
        this.name = name;
        this.result = result;
    }
}
