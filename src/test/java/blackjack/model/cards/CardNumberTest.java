package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardNumberTest {
    @DisplayName("카드 숫자가 Ace인지 판별한다")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "TWO,false", "THREE,false"})
    void isAce(CardNumber given, boolean expected) {
        boolean result = given.isAce();

        assertThat(result).isEqualTo(expected);
    }
}
