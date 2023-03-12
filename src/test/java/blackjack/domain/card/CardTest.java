package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드가 ACE이면 참이 반환되어야 한다.")
    void isAce_true() {
        // given
        Card card = Card.of(Suit.DIAMOND, Rank.ACE);

        // expect
        assertThat(card.isAce())
                .isTrue();
    }

    @Test
    @DisplayName("카드가 ACE가 아니면 거짓이 반환되어야 한다.")
    void isAce_false() {
        // given
        Card card = Card.of(Suit.DIAMOND, Rank.TWO);

        // expect
        assertThat(card.isAce())
                .isFalse();
    }
}
