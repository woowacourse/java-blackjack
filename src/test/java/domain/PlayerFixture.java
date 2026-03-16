package domain;

import java.util.List;

public class PlayerFixture {
    public static Player createDefault(String name) {
        return Player.of(Name.from(name), new Betting(10000),
                new Hand(List.of(
                        Card.of(CardNumber.J, CardShape.CLOVER),
                        Card.of(CardNumber.Q, CardShape.CLOVER))));
    }

    public static Player createBlackjack(String name) {
        return Player.of(Name.from(name), new Betting(10000),
                HandFixture.createBlackjack());
    }

    public static Player createBust(String name) {
        return Player.of(Name.from(name), new Betting(10000),
                HandFixture.createBust());
    }

    public static Player createBlackjackScore(String name) {
        return Player.of(Name.from(name), new Betting(10000),
                HandFixture.createBlackjackScore());
    }
}
