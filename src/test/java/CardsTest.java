import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsTest {

    @DisplayName("카드를 추가할 수 있다")
    @Test
    void test1() {
        //given
        Cards cards = Cards.empty();
        Card card = new Card(CardNumber.TWO, CardShape.CLOVER);
        //when
        cards.add(card);
        //then
        assertThat(cards.getCards()).contains(card);
    }

    @DisplayName("숫자 카드의 합을 구할 수 있다")
    @ParameterizedTest
    @CsvSource({
            "TWO, THREE, 5",
            "TWO, FOUR, 6",
            "TWO, FIVE, 7",
            "TEN, NINE, 19"
    })
    void 숫자_카드_합_구하기(CardNumber number1, CardNumber number2, int expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2));
        //when
        int actual = cards.sum();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("A를 제외한 문자 카드의 합을 구할 수 있다")
    @ParameterizedTest
    @CsvSource({
            "KING, THREE, 13",
            "JACK, TEN, 20",
            "QUEEN, KING, 20"
    })
    void 문자_카드_합_구하기(CardNumber number1, CardNumber number2, int expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2));

        //when
        int actual = cards.sum();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("A를 1로 판단할 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "A, JACK, TEN, 21",
            "A, QUEEN, FIVE, 16",
            "A, SIX, FIVE, 12"
    })
    void 문자_카드_합_구하기(CardNumber number1, CardNumber number2, CardNumber number3, int expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Card card3 = new Card(number3, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2, card3));

        //when
        int actual = cards.sum();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
