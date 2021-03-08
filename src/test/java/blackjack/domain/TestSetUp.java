package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestSetUp {
    public static final Player WINNER;
    public static final Player LOSER;
    public static final Player TIE_PLAYER;
    public static final Player BUST_PLAYER;
    public static final Dealer DEALER;
    public static final Dealer BUST_DEALER;

    static {
        WINNER = new Player("winner", new Hand(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.ACE))));
        LOSER = new Player("loser", new Hand(Collections.emptyList()));
        TIE_PLAYER = new Player("tie player", new Hand(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN))));
        BUST_PLAYER = new Player("bust player", new Hand(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN),
                Card.valueOf(Shape.SPADE, CardValue.JACK))));
        DEALER = new Dealer(new Hand(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN))));
        BUST_DEALER = new Dealer(new Hand(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN),
                Card.valueOf(Shape.SPADE, CardValue.JACK))));
    }

    public static Player createWinner() {
        Player winner = new Player("winner");
        for (Card card : getWinningDeck()) {
            winner.draw(card);
        }
        return winner;
    }

    private static List<Card> getWinningDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.ACE)));
    }
}
