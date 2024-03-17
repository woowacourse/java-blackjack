package domain.participant.player;

import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;

public class PlayerFixture {

    public static Player of(String name, int bet) {
        return new Player(new Name(name), new Bet(bet));
    }

    public static Player from(String name) {
        return of(name, 1000);
    }
}
