package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.MockDeck;

public class DealerTest {

    @Nested
    @DisplayName("isDrawable은")
    class IsDrawable {

        @ParameterizedTest
        @CsvSource(value = {"SIX|true", "SEVEN|false"}, delimiter = '|')
        @DisplayName("패의 합이 17이 넘는지 유무를 알려준다.")
        void returnFalse(CardNumber cardNumber, boolean expected) {
            Dealer dealer = new Dealer();
            MockDeck mockDeck = new MockDeck(
                List.of(Card.of(CardPattern.CLOVER, CardNumber.TEN), Card.of(CardPattern.CLOVER, cardNumber)));
            dealer.drawCard(mockDeck);
            dealer.drawCard(mockDeck);

            Assertions.assertThat(dealer.isDrawable()).isEqualTo(expected);
        }
    }
}
