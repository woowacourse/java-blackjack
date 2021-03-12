package blackjack.domain.cards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CardTest {

    @Test
    @DisplayName("캐싱 및 동일성 테스트")
    void caching() {
        assertThat(Card.valueOf(Shape.DIAMOND, CardValue.ACE))
            .isSameAs((Card.valueOf(Shape.DIAMOND, CardValue.ACE)));
    }

    @Test
    @DisplayName("모든 카드가 존재하는지 테스트")
    void getAllCards() {
        assertThat(Card.getAllCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("점수 확인 테스트")
    void getCardValue() {
        assertThat(Card.valueOf(Shape.SPADE, CardValue.ACE).getScore()).isEqualTo(1);
        assertThat(Card.valueOf(Shape.SPADE, CardValue.JACK).getScore()).isEqualTo(10);
        assertThat(Card.valueOf(Shape.SPADE, CardValue.QUEEN).getScore()).isEqualTo(10);
        assertThat(Card.valueOf(Shape.SPADE, CardValue.KING).getScore()).isEqualTo(10);
        assertThat(Card.valueOf(Shape.SPADE, CardValue.NINE).getScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("카드 이름 테스트")
    void getCardName() {
        assertThat(Card.valueOf(Shape.SPADE, CardValue.ACE).getCardName()).isEqualTo("A스페이드");
    }

    @Test
    @DisplayName("여러 개의 점수를 가질 수 있는 카드 테스트")
    void hasMultipleValue() {
        assertThat(Card.valueOf(Shape.SPADE, CardValue.ACE).hasMultipleValue()).isTrue();
        assertThat(Card.valueOf(Shape.SPADE, CardValue.KING).hasMultipleValue()).isFalse();
    }
}
