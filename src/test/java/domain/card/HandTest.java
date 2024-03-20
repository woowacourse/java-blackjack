package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {
    @DisplayName("점수를 계산한다(ACE,ACE,ACE -> 13).")
    @Test
    void score() {
        final Hand hand = new Hand(
                new Card(Denomination.ACE, Suit.CLUBS),
                new Card(Denomination.ACE, Suit.CLUBS),
                new Card(Denomination.ACE, Suit.CLUBS)
        );

        final int score = hand.score().toInt();

        assertEquals(13, score);
    }

    @DisplayName("점수를 계산한다(ACE,ACE,KING -> 22).")
    @Test
    void score2() {
        final Hand hand = new Hand(
                new Card(Denomination.ACE, Suit.CLUBS),
                new Card(Denomination.ACE, Suit.CLUBS),
                new Card(Denomination.KING, Suit.CLUBS)
        );

        final int score = hand.score().toInt();

        assertEquals(22, score);
    }

    @DisplayName("점수를 계산한다(KING,JACK,ACE -> 21).")
    @Test
    void score3() {
        final Hand hand = new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.KING, Suit.DIAMOND),
                new Card(Denomination.ACE, Suit.CLUBS)
        );

        final int score = hand.score().toInt();

        assertEquals(21, score);
    }


    @DisplayName("점수를 계산한다(KING,ACE -> 21).")
    @Test
    void score4() {
        final Hand hand = new Hand(
                new Card(Denomination.KING, Suit.CLUBS),
                new Card(Denomination.ACE, Suit.CLUBS)
        );

        final int score = hand.score().toInt();

        assertEquals(21, score);
    }
}
