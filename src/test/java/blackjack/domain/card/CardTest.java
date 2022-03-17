package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = Card.of(Denomination.ACE, Suit.SPADE);
    }

    @Test
    @DisplayName("같은 숫자인지 확인하는 기능 True 테스트")
    void isSameDenomination() {
        assertThat(card.isSameDenomination(Denomination.ACE))
                .isTrue();
    }

    @Test
    @DisplayName("같은 숫자인지 확인하는 기능 False 테스트")
    void isSameDenominationFalse() {
        assertThat(card.isSameDenomination(Denomination.TEN))
                .isFalse();
    }

    @Test
    @DisplayName("숫자확인 기능 테스트")
    void getDenomination() {
        assertThat(card.getDenomination())
                .isEqualTo(Denomination.ACE);
    }

    @Test
    @DisplayName("동일 카드인지 확인 기능 테스트")
    void testEquals() {
        assertThat(Card.of(Denomination.TEN, Suit.SPADE))
                .isEqualTo(Card.of(Denomination.TEN, Suit.SPADE));
    }
}
