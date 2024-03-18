package blackjack.domain.gamer.player;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;

public class Player extends Gamer {
    private final PlayerName name;

    private Player(PlayerName name, Deck deck) {
        super(deck);
        this.name = name;
    }

    public static Player of(String name, Deck deck) {
        return new Player(new PlayerName(name), deck);
    }

    public boolean canContinue() {
        return !isBlackjack() && isUnderBound();
    }

    public String getName() {
        return name.get();
    }
}
