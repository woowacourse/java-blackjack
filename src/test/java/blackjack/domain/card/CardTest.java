package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("ACE 랭크의 카드는 isAce가 참이다")
    void isAce_ShouldReturnTrue_WhenRankIsAce() {
        Card aceCard = new Card(Suit.CLOVER, Rank.ACE);
        assertThat(aceCard.isAce()).isTrue();
    }

    @Test
    @DisplayName("ACE가 아닌 랭크의 카드는 isAce가 거짓이다")
    void isAce_ShouldReturnFalse_WhenRankIsNotAce() {
        Card kingCard = new Card(Suit.CLOVER, Rank.KING);
        assertThat(kingCard.isAce()).isFalse();
    }

    @Test
    @DisplayName("ACE 바로 다음 순서인 TWO 카드는 isAce가 거짓이다")
    void isAce_ShouldReturnFalse_WhenRankIsTwo() {
        Card twoCard = new Card(Suit.CLOVER, Rank.TWO);
        assertThat(twoCard.isAce()).isFalse();
    }
}
