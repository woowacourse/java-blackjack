package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @Test
    @DisplayName("카드 숫자 13개를 반환할 수 있다.")
    void cardNumbers() {
        final List<CardNumber> cardNumbers = CardNumber.cardNumbers();
        final int distinctCount = (int) cardNumbers.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(13);
    }

}