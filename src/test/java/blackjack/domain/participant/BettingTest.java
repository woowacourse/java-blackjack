package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("1.5배의 배팅금액을 받는다.")
    @Test
    void getBlackjackAmount() {
        //given
        int amount = 1000;
        double multiplier = 1.5;

        //when
        Betting betting = new Betting(String.valueOf(amount));

        //then
        assertThat(betting.getBlackjackAmount()).isEqualTo(String.valueOf(amount * multiplier));
    }
}
