package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardNumberTest {

    @DisplayName("각 카드는 점수를 가진다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE:1", "TWO:2", "THREE:3", "FOUR:4", "FIVE:5", "SIX:6", "SEVEN:7", "EIGHT:8", "NINE:9",
            "TEN:10", "JACK:10", "QUEEN:10", "KING:10"}, delimiter = ':')
    void 모든_카드_점수_존재(CardNumber cardNumber, int score) {
        assertThat(cardNumber.getScore()).isEqualTo(score);
    }
}
