package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("점수 반환 테스트")
    void cardScoreTest() {
        // given
        Card card = new Card(Rank.K, Suit.DIAMOND);

        // when
        int score = card.score();

        // then
        assertThat(score).isEqualTo(10);
    }

    @Test
    @DisplayName("에이스 판별 테스트")
    void isAceTest() {
        // given
        Card card1 = new Card(Rank.ACE, Suit.CLOVER);
        Card card2 = new Card(Rank.K, Suit.CLOVER);

        // when & then
        assertThat(card1.isAce()).isTrue();
        assertThat(card2.isAce()).isFalse();
    }
}