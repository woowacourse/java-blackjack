import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class CardTest {

    @DisplayName("카드는 점수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE:1", "TWO:2", "THREE:3", "FOUR:4", "FIVE:5", "SIX:6", "SEVEN:7", "EIGHT:8", "NINE:9", "TEN:10", "JACK:10", "QUEEN:10", "KING:10"}, delimiter = ':')
    void 카드_점수_반환(CardNumber cardNumber, int score){
        Card card = new Card(Symbol.HEART, cardNumber);
        assertThat(card.getScore()).isEqualTo(score);
    }
}
