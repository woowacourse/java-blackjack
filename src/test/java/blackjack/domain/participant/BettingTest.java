package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("배팅 금액")
class BettingTest {

    @DisplayName("적절하지 않은 가격 형식에 에외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000", "", "   ", "asd", "12emcsaf4"})
    void createBettingException(final String amount) {
        //given & when & then
        assertThatThrownBy(() -> new Betting(amount)).isInstanceOf(IllegalArgumentException.class);
    }
}
