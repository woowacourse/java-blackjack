package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultTest {

    @Test
    @DisplayName("승리하면 배팅한 금액만큼 돈을 더 받는다.")
    void win_case() {
        // given
        BlackJackResult win = BlackJackResult.WIN;
        int givenMoney = 1000;
        // when
        double actual = win.calculatePrize(givenMoney);
        // then
        assertThat(actual).isEqualTo(1000);
    }

    @Test
    @DisplayName("패배한 경우 돈을 모두 잃는다")
    void lose_case() {
        // given
        BlackJackResult win = BlackJackResult.LOSE;
        int givenMoney = 1000;
        // when
        double actual = win.calculatePrize(givenMoney);
        // then
        assertThat(actual).isEqualTo(-1000);
    }

    @Test
    @DisplayName("블랙잭인 경우 돈을 1.5배로 얻는다")
    void blackJack_case() {
        // given
        BlackJackResult win = BlackJackResult.BLACKJACK;
        int givenMoney = 1000;
        // when
        double actual = win.calculatePrize(givenMoney);
        // then
        assertThat(actual).isEqualTo(1500);
    }

    @Test
    @DisplayName("서로 블랙잭인 경우 돈을 그대로 받는다")
    void each_blackJack_case() {
        // given
        BlackJackResult win = BlackJackResult.EACH_BLACKJACK;
        int givenMoney = 1000;
        // when
        double actual = win.calculatePrize(givenMoney);
        // then
        assertThat(actual).isEqualTo(0);
    }
}

