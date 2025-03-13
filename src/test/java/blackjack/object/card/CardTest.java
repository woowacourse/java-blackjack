package blackjack.object.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {
    @DisplayName("카드를 생성한다")
    @CsvSource(value = {"HEART:TEN", "DIAMOND:KING"}, delimiterString = ":")
    @ParameterizedTest
    void createTest(CardShape shape, CardType type) {
        //when
        Card card = new Card(shape, type);

        //then
        assertThat(card).extracting("shape").isEqualTo(shape);
        assertThat(card).extracting("type").isEqualTo(type);
    }

    @DisplayName("상태가 같으면 같은 객체이다.")
    @Test
    void equalsTest() {
        Card card1 = new Card(CardShape.DIAMOND, CardType.ACE);
        Card card2 = new Card(CardShape.DIAMOND, CardType.ACE);

        assertThat(card1).isEqualTo(card2);
    }
}
