package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("Ace가 아니면 false를 리턴한다.")
    void givenNotAce_thenReturnFalse() {
        //given
        final Card card = Card.of(Suit.HEARTS, Rank.JACK);

        //when
        final boolean isAce = card.isAce();

        //then
        assertThat(isAce).isFalse();
    }

    @Test
    @DisplayName("Ace면 true를 리턴한다.")
    void givenAce_thenReturnTrue() {
        //given
        final Card card = Card.of(Suit.CLUBS, Rank.ACE);

        //when
        final boolean isAce = card.isAce();

        //then
        assertThat(isAce).isTrue();
    }
}
