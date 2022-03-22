package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Fixtures;
import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    private final Card card = Fixtures.ACE;

    @Test
    @DisplayName("같은 숫자인지 확인하는 기능 True 테스트")
    void isSameDenomination() {
        assertThat(card.isAce())
                .isTrue();
    }

    @Test
    @DisplayName("같은 숫자인지 확인하는 기능 False 테스트")
    void isSameDenominationFalse() {
        assertThat(Fixtures.TEN.isAce())
                .isFalse();
    }

    @Test
    @DisplayName("숫자확인 기능 테스트")
    void getDenomination() {
        assertThat(card.getDenomination())
                .isEqualTo("A");
    }

    @Test
    @DisplayName("문양확인 기능 테스트")
    void getSuit() {
        assertThat(card.getSuit())
                .isEqualTo("클로버");
    }

    @Test
    @DisplayName("포인트확인 기능 테스트")
    void getPoint() {
        assertThat(card.getPoint())
                .isEqualTo(11);
    }

    @Test
    @DisplayName("동일 카드인지 확인 기능 테스트")
    void testEquals() {
        assertThat(Card.of(Denomination.TEN, Suit.CLOVER))
                .isEqualTo(Card.of(Denomination.TEN, Suit.CLOVER));
    }
}
