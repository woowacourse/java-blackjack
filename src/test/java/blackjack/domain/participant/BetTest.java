package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {

    @DisplayName("베팅 객체 생성자 테스트")
    @Test
    void constructor_CreateBet_HasInstance() {
        Bet bet = new Bet(1000);

        assertThat(bet).isNotNull();
    }

    @DisplayName("최소 베팅금액 미만으로 베팅 객체 생성 시 예외 발생")
    @Test
    void constructor_CreateBet_ThrowsIllegalArgumentException() {

        assertThatThrownBy(() -> new Bet(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 베팅금액은 1000원 입니다.");
    }

    @DisplayName("패배 시 베팅만큼의 손해 발생")
    @Test
    void getLosingMoney_Betting10000_ReturnsNegative10000() {
        Bet bet = new Bet(10000);

        assertThat(bet.getLosingMoney()).isEqualTo(-10000);
    }

    @DisplayName("무승부 시 0 반환")
    @Test
    void getDrawMoney_Betting10000_ReturnsZero() {
        Bet bet = new Bet(10000);

        assertThat(bet.getDrawMoney()).isEqualTo(0);
    }

    @DisplayName("승리 시 베팅만큼 수익 발생")
    @Test
    void getWinningPrize_Betting10000_Returns10000() {
        Bet bet = new Bet(10000);

        assertThat(bet.getWinningPrize()).isEqualTo(10000);
    }

    @DisplayName("블랙잭으로 승리 시 베팅의 1.5배만큼 수익 발생")
    @Test
    void getBlackJackPrize_Betting10000_Returns15000() {
        Bet bet = new Bet(10000);

        assertThat(bet.getBlackJackPrize()).isEqualTo(15000);
    }
}
