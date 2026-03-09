package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("ACE 랭크의 카드는 isAce가 참이다")
    void isAce_ShouldReturnTrue_WhenRankIsAce() {
        // given
        Card aceCard = new Card(Suit.CLOVER, Rank.ACE);

        // when
        boolean result = aceCard.isAce();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("ACE가 아닌 랭크의 카드는 isAce가 거짓이다")
    void isAce_ShouldReturnFalse_WhenRankIsNotAce() {
        // given
        Card kingCard = new Card(Suit.CLOVER, Rank.KING);

        // when
        boolean result = kingCard.isAce();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("ACE 바로 다음 순서인 TWO 카드는 isAce가 거짓이다")
    void isAce_ShouldReturnFalse_WhenRankIsTwo() {
        // given
        Card twoCard = new Card(Suit.CLOVER, Rank.TWO);

        // when
        boolean result = twoCard.isAce();

        // then
        assertThat(result).isFalse();
    }
}
