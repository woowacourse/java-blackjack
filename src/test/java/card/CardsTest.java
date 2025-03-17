package card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("에이스가 없는 카드들의 최적 합을 반환한다.")
    void test1() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardShape.DIAMOND, CardNumber.JACK));
        cards.add(new Card(CardShape.DIAMOND, CardNumber.QUEEN));

        // when
        int result = cards.calculateNearestTotal();

        // then
        assertThat(result)
                .isEqualTo(20);
    }

    @Test
    @DisplayName("에이스가 있는 카드들의 최적 합을 반환한다.")
    void test2() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardShape.DIAMOND, CardNumber.JACK));
        cards.add(new Card(CardShape.DIAMOND, CardNumber.ACE));

        // when
        int result = cards.calculateNearestTotal();

        // then
        assertThat(result)
                .isEqualTo(21);
    }

    @Test
    @DisplayName("에이스가 있는 카드들의 21이 넘지 않는 최적의 합을 반환한다.")
    void test3() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        cards.add(new Card(CardShape.DIAMOND, CardNumber.ACE));
        cards.add(new Card(CardShape.CLOVER, CardNumber.ACE));

        // when
        int result = cards.calculateNearestTotal();

        // then
        assertThat(result)
                .isEqualTo(20);
    }

    @Test
    @DisplayName("Bust시 최소 합을 반환한다.")
    void test4() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardShape.DIAMOND, CardNumber.ACE));
        cards.add(new Card(CardShape.DIAMOND, CardNumber.QUEEN));
        cards.add(new Card(CardShape.CLOVER, CardNumber.KING));
        cards.add(new Card(CardShape.CLOVER, CardNumber.THREE));

        // when
        int result = cards.calculateNearestTotal();

        // then
        assertThat(result)
                .isEqualTo(24);
    }
}
