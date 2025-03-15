package blackjack.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackBettingTest {
    
    @Test
    void 블랙잭_베팅이_1000원_미만이면_예외가_발생한다() {
        // given
        final int bettingAmount = 999;
        
        // expected
        assertThatThrownBy(() -> new BlackjackBetting(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 이상, 100000원 이하여야 합니다.");
    }
    
    @Test
    void 블랙잭_베팅이_100000원_초과이면_예외가_발생한다() {
        // given
        final int bettingAmount = 100001;
        
        // expected
        assertThatThrownBy(() -> new BlackjackBetting(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 이상, 100000원 이하여야 합니다.");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1500, 2500, 90050})
    void 블랙잭_베팅이_1000원_단위가_아니면_예외가_발생한다(int bettingAmount) {
        // given
        
        // expected
        assertThatThrownBy(() -> new BlackjackBetting(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 단위여야 합니다.");
    }
    
    @Test
    void 플레이어가_승리하면_수익은_1배이다() {
        // given
        final WinningStatus status = WinningStatus.플레이어_승리;
        final boolean isBlackjackWin = false;
        final BlackjackBetting blackjackBetting = new BlackjackBetting(1000);
        
        // when
        final double result = blackjackBetting.calculateIncome(status, isBlackjackWin);
        
        // then
        assertThat(result).isEqualTo(1000);
    }
    
    @Test
    void 플레이어가_패배하면_수익은_마이너스_1배이다() {
        // given
        final WinningStatus status = WinningStatus.플레이어_패배;
        final boolean isBlackjackWin = false;
        final BlackjackBetting blackjackBetting = new BlackjackBetting(1000);
        
        // when
        final double result = blackjackBetting.calculateIncome(status, isBlackjackWin);
        
        // then
        assertThat(result).isEqualTo(-1000);
    }
    
    @Test
    void 무승부면_수익은_0배이다() {
        // given
        final WinningStatus status = WinningStatus.무승부;
        final boolean isBlackjackWin = false;
        final BlackjackBetting blackjackBetting = new BlackjackBetting(1000);
        
        // when
        final double result = blackjackBetting.calculateIncome(status, isBlackjackWin);
        
        // then
        assertThat(result).isEqualTo(0);
    }
    
    @Test
    void 플레이어가_블랙잭으로_이기면_수익은_1쩜5배이다() {
        // given
        final WinningStatus status = WinningStatus.플레이어_승리;
        final boolean isBlackjackWin = true;
        final BlackjackBetting blackjackBetting = new BlackjackBetting(1000);
        
        // when
        final double result = blackjackBetting.calculateIncome(status, isBlackjackWin);
        
        // then
        assertThat(result).isEqualTo(1500);
    }
}
