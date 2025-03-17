package blackjack.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AceCardTest {

    @ParameterizedTest
    @CsvSource({
            "10, 11",  // 누적합이 10이면 10 + 11 = 21이므로 11 반환
            "11, 1",   // 누적합이 11이면 11 + 11 = 22 > 21 이므로 1 반환
            "0, 11",   // 누적합이 0이면 0 + 11 = 11 이므로 11 반환
            "20, 1"    // 누적합이 20이면 20 + 11 = 31 > 21 이므로 1 반환
    })
    @DisplayName("에이스는 현재 점수에 따라서 1과 11 중에서 더 유리한 수로 반환한다")
    void shouldReturnAdvantageousAcePoint(int accumulatedSum, int expectedAceValue) {
        // given
        AceCard aceCard = (AceCard) CardFixture.createCard(CardSuit.SPADE, CardRank.ACE);

        // when
        int result = aceCard.getPoint(accumulatedSum);

        // then
        assertThat(expectedAceValue).isEqualTo(result);
    }
}
