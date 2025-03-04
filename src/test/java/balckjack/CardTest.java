package balckjack;

import static org.assertj.core.api.Assertions.assertThat;

import balckjack.card.Card;
import balckjack.card.CardShape;
import balckjack.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {
    @DisplayName("카드를 생성한다")
    @CsvSource(value = {"HEART:TEN", "DIAMOND:KING"}, delimiterString = ":")
    @ParameterizedTest
    void createTest(CardShape shape, CardValue value) {
        //when
        Card card = new Card(shape, value);

        //then
        assertThat(card).extracting("shape").isEqualTo(shape);
        assertThat(card).extracting("value").isEqualTo(value);
    }
}
