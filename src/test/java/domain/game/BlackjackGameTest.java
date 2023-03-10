package domain.game;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class BlackjackGameTest {

    @Test
    void hit() {
        final var hit = BlackjackGame.start(
                Card.of(Suit.SPADE, Denomination.TWO),
                Card.of(Suit.SPADE, Denomination.THREE)
        );

        assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    void bust() {
        final var hit = BlackjackGame.start(
                Card.of(Suit.SPADE, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.TEN)
        );

        final var bust = hit.draw(Card.of(Suit.SPADE, Denomination.TEN));

        assertThat(bust).isInstanceOf(Bust.class);
    }

    @Test
    void hitCards() {;

        final var hit = BlackjackGame.start(
                Card.of(Suit.SPADE, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.TEN)
        );

        final var cards = hit.cards();

        assertThat(cards).isEqualTo(List.of(Card.of(Suit.SPADE, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.TEN)));
    }

    @Test
    void bustDraw() {
        final var hit = BlackjackGame.start(
                Card.of(Suit.SPADE, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.TEN)
        );

        final var bust = hit.draw(Card.of(Suit.SPADE, Denomination.TEN));

        assertThatThrownBy(() -> bust.draw(Card.of(Suit.SPADE, Denomination.TEN)))
                .isInstanceOf(IllegalStateException.class);
    }
}
