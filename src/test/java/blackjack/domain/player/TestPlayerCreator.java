package blackjack.domain.player;

import blackjack.domain.card.TestHandCreator;

public class TestPlayerCreator {

    private TestPlayerCreator() {
    }

    public static Player of(String name, int... hand) {
        return new Player(new PlayerName(name), TestHandCreator.of(hand));
    }
}
