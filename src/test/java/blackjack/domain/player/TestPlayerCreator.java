package blackjack.domain.player;

public class TestPlayerCreator {

    public static Player of(String name, int... hand) {
        return new Player(new PlayerName(name), TestHandCreator.of(hand));
    }
}
