package domain.fixture;

import domain.card.CardArea;
import domain.player.Name;
import domain.player.Player;

public class PlayerFixture {

    public static Player 말랑(final CardArea cardArea) {
        return new Player(Name.of("말랑"), cardArea);
    }

    public static Player 콩떡(final CardArea cardArea) {
        return new Player(Name.of("콩떡"), cardArea);
    }

    public static Player 코다(final CardArea cardArea) {
        return new Player(Name.of("코다"), cardArea);
    }
}
