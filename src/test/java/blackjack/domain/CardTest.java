package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
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
}
