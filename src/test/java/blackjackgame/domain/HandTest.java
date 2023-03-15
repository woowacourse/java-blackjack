package blackjackgame.domain;

import static blackjackgame.domain.CardFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Hand;

class HandTest {

    @Test
    void create() {
        assertDoesNotThrow(() -> new Hand(List.of(CLOVER_TWO, CLOVER_FIVE)));
    }

    @Test
    void twoTen() {
        final var hand = new Hand(
            CLOVER_TWO,
            CLOVER_KING);

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(12));
    }

    @Test
    void twoThreeFour() {
        final var hand = new Hand(
            CLOVER_TWO,
            CLOVER_THREE,
            CLOVER_FOUR);

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(9));
    }

    @Test
    void tenTenTwo() {
        final var hand = new Hand(
            CLOVER_KING,
            CLOVER_KING,
            CLOVER_TWO
        );

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(22));
    }

    @Test
    void aceOne() {
        final var hand = new Hand(
            CLOVER_ACE,
            CLOVER_TWO
        );

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(13));
    }

    @Test
    void tenNineAceAce() {
        final var hand = new Hand(
            CLOVER_KING,
            CLOVER_NINE,
            CLOVER_ACE,
            CLOVER_ACE
        );

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(21));
    }

    @Test
    void aceTen() {
        final var hand = new Hand(
            CLOVER_ACE,
            CLOVER_KING
        );

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(21));
    }

    @Test
    void aceAceAceAce() {
        final var hand = new Hand(
            CLOVER_ACE,
            CLOVER_ACE,
            CLOVER_ACE,
            CLOVER_ACE
        );

        final var score = hand.score();

        assertThat(score).isEqualTo(new Score(14));
    }

    @Test
    void isNotBust() {
        final var hand = new Hand(
            CLOVER_KING,
            CLOVER_KING,
            CLOVER_ACE
        );

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void isBlackJack() {
        final var hand = new Hand(
            CLOVER_KING,
            CLOVER_ACE
        );

        assertThat(hand.isBlackJack()).isTrue();
    }
}
