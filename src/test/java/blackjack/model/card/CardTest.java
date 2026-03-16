package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void cardTest() {
        // given
        Rank ace = Rank.ACE;
        Suit clover = Suit.CLOVER;

        // when & then
        assertThatCode(() -> Card.of(ace, clover)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 기본 점수 가져오기")
    void getCardDefaultScoreTest() {
        // given
        Card card = Card.of(Rank.TEN, Suit.CLOVER);

        // when
        int score = card.getDefaultScore();

        // then
        assertThat(score).isEqualTo(10);
    }

    @Test
    @DisplayName("에이스 판별 테스트")
    void isAceTest() {
        // given
        Card card1 = Card.of(Rank.ACE, Suit.CLOVER);
        Card card2 = Card.of(Rank.TEN, Suit.CLOVER);

        // when & then
        assertThat(card1.isAce()).isTrue();
        assertThat(card2.isAce()).isFalse();
    }
}
