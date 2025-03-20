package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("에이스가 없는 카드 뭉치의 합을 구할 수 있다.")
    void test1() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardType.DIAMOND, CardNumber.JACK));
        cards.add(new Card(CardType.DIAMOND, CardNumber.QUEEN));

        // when
        int result = cards.calculateScore();

        // then
        Assertions.assertThat(result)
                .isEqualTo(20);
    }

    @Test
    @DisplayName("에이스가 있는 카드 뭉치의 최적합을 구할 수 있다.")
    void test2() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardType.DIAMOND, CardNumber.JACK));
        cards.add(new Card(CardType.DIAMOND, CardNumber.ACE));

        // when
        int result = cards.calculateScore();

        // then
        Assertions.assertThat(result)
                .isEqualTo(21);
    }

    @Test
    @DisplayName("에이스가 있는 경우 21이 넘지 않는 최적의 합을 구할 수 있다.")
    void test3() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardType.DIAMOND, CardNumber.EIGHT));
        cards.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        cards.add(new Card(CardType.CLOVER, CardNumber.ACE));

        // when
        int result = cards.calculateScore();

        // then
        Assertions.assertThat(result)
                .isEqualTo(20);
    }

    @Test
    @DisplayName("Bust시 최소 합을 반환한다.")
    void test4() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        cards.add(new Card(CardType.DIAMOND, CardNumber.QUEEN));
        cards.add(new Card(CardType.CLOVER, CardNumber.KING));
        cards.add(new Card(CardType.CLOVER, CardNumber.THREE));

        // when
        int result = cards.calculateScore();

        // then
        Assertions.assertThat(result)
                .isEqualTo(24);
    }
}
