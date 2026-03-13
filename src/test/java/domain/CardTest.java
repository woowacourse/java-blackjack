package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class CardTest {
    @Test
    void 에이스_카드_점수는_11() {
        // given
        Card card = new Card(Suit.SPADE, Rank.ACE);

        // when
        int score = card.getCardScore();

        // then
        assertThat(score).isEqualTo(11);
    }

    @Test
    void 킹_카드_점수는_10() {
        // given
        Card card = new Card(Suit.HEART, Rank.KING);

        // when
        int score = card.getCardScore();

        // then
        assertThat(score).isEqualTo(10);
    }

    @Test
    void 숫자_카드_점수_반환() {
        // given
        Card card = new Card(Suit.DIAMOND, Rank.SEVEN);

        // when
        int score = card.getCardScore();

        // then
        assertThat(score).isEqualTo(7);
    }

    @Test
    void 에이스_카드_확인() {
        // given
        Card aceCard = new Card(Suit.SPADE, Rank.ACE);
        Card kingCard = new Card(Suit.SPADE, Rank.KING);

        // when & then
        assertThat(aceCard.isAceCard()).isTrue();
        assertThat(kingCard.isAceCard()).isFalse();
    }

    @Test
    void 카드_랭크_이름_반환() {
        // given
        Card card = new Card(Suit.SPADE, Rank.ACE);

        // when & then
        assertThat(card.getRankName()).isEqualTo("A");
    }

    @Test
    void 카드_수트_이름_반환() {
        // given
        Card card = new Card(Suit.SPADE, Rank.ACE);

        // when & then
        assertThat(card.getSuitName()).isEqualTo("스페이드");
    }
}
