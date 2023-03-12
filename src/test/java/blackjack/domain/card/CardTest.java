package blackjack.domain.card;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드는 모양과 숫자를 지닌다.")
    void initTest() {
        final Card card = new Card(CardShape.SPADE, CardNumber.KING);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(card.getNumber()).isEqualTo(CardNumber.KING);
            softly.assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
        });
    }
}
