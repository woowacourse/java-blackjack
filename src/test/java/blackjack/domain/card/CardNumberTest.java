package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @Test
    @DisplayName("카드 숫자 13개를 반환할 수 있다.")
    void cardNumbers() {
        final List<CardNumber> cardNumbers = CardNumber.allNumbers();
        final int distinctCount = (int) cardNumbers.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(13);
    }

    @Test
    @DisplayName("카드 숫자들을 받아, 합을 반환한다. J, Q, K는 10으로 계산하고 A는 총합이 21이 넘는다면 1로 계산할 수 있다.")
    void calculateScoreAIsValueOfOne() {
        final int result = CardNumber.calculateScore(Arrays.asList(A, SIX, NINE));
        assertThat(result).isEqualTo(16);
    }

    @Test
    @DisplayName("카드 숫자들을 받아, 합을 반환한다. J, Q, K는 10으로 계산하고 A는 총합이 21이 넘지 않는다면 11로 계산할 수 있다.")
    void calculateScoreAIsValueOfEleven() {
        final int result = CardNumber.calculateScore(Arrays.asList(A, THREE, SEVEN));
        assertThat(result).isEqualTo(21);
    }
}
