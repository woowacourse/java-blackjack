package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardLetterTest {
    @ParameterizedTest
    @CsvSource(value = {"ACE,A,11,1", "TWO,2,2,2", "TEN,10,10,10", "JACK,J,10,10"})
    @DisplayName("getter 정상 작동에 대한 테스트")
    void getTypeTest(final String cardLetter, final String letter, final int value, final int extraValue) {
        final CardLetter testCardLetter = CardLetter.valueOf(cardLetter);
        assertThat(testCardLetter.getLetter()).isEqualTo(letter);
        assertThat(testCardLetter.getValue()).isEqualTo(value);
        assertThat(testCardLetter.getExtraValue()).isEqualTo(extraValue);
    }
}
