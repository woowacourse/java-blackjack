package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 에이스_카드는_문양을_가지고_있다() {
        // given
        Card card = new Card(CardShape.CLOVER, CardType.ACE);

        // when
        CardShape cardShape = card.getShape();

        // then
        assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }

    @Test
    void 에이스_카드_점수를_반환한다() {
        // given
        Card card = new Card(CardShape.CLOVER, CardType.ACE);

        // when
        int cardPoint = card.getPoint();
        // then
        assertThat(cardPoint).isEqualTo(1);
    }

    @Test
    void 스페셜_카드는_문양을_가지고_있다() {
        // given
        Card card = new Card(CardShape.CLOVER, CardType.JACK);

        // when
        CardShape cardShape = card.getShape();

        // then
        assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }

    @Test
    void 스페셜_카드_점수를_반환한다() {
        // given
        Card card = new Card(CardShape.CLOVER, CardType.JACK);

        // when
        int cardPoint = card.getPoint();
        // then
        assertThat(cardPoint).isEqualTo(10);
    }

    @Test
    void 일반_카드는_문양을_가지고_있다() {
        // given
        Card card = new Card(CardShape.CLOVER, CardType.NORMAL_8);

        // when
        CardShape cardShape = card.getShape();

        // then
        assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }

    @Test
    void 일반_카드_점수를_반환한다() {
        // given
        Card card = new Card(CardShape.CLOVER, CardType.NORMAL_2);

        // when
        int cardPoint = card.getPoint();
        // then
        assertThat(cardPoint).isEqualTo(2);
    }
}
