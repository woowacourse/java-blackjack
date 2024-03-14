package blackjack.model;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import blackjack.model.participant.Hand;
import blackjack.model.participant.Player;
import java.util.List;

public enum PlayerFixture {

    BLACKJACK_PLAYER(Player.of("몰리",
            new Hand(List.of(new Card(Shape.DIA, Score.TEN), new Card(Shape.DIA, Score.ACE))))),
    BUST_PLAYER(Player.of("몰리",
            new Hand(List.of(new Card(Shape.DIA, Score.TEN), new Card(Shape.DIA, Score.ACE), new Card(Shape.DIA, Score.JACK))))),
    NOT_BLACKJACK_21_PLAYER(Player.of("몰리",
            new Hand(List.of(new Card(Shape.DIA, Score.TEN), new Card(Shape.DIA, Score.SIX), new Card(Shape.DIA, Score.FIVE))))),
    UNDER_21_PLAYER(Player.of("몰리",
            new Hand(List.of(new Card(Shape.DIA, Score.TEN), new Card(Shape.DIA, Score.JACK))))),
    ;

    private final Player player;

    PlayerFixture(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
