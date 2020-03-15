package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

import java.util.Objects;

public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public BlackJackResult match(Dealer dealer) {
        return this.hand.match(dealer.hand);
    }
}