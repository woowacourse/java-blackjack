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

    @DisplayName("카드의 합을 구할 수 있다")
    @ParameterizedTest
    @CsvSource({
            "TWO, THREE, 5",
    })
    void test2(CardNumber number1, CardNumber number2, int expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2));
        //when
        int actual = cards.sum();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
