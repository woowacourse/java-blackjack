package blackjack;

import java.util.List;

public class Game {
    private final Players players;
    private final TrumpCardPack trumpCardPack;

    private Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = Players.from(names);
    }
}
