package blackjack.domain.game;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BettingTest {

    @Test
    @DisplayName("성공: 양수 액수 배팅")
    void getProfitOf_NoException_PlusBetting() {
        Player player = Player.nameOf("name");
        Betting betting = new Betting();
        assertThatCode(() -> betting.bet(player, 1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: 음수 액수 배팅")
    void getProfitOf_Exception_MinusBetting() {
        Player player = Player.nameOf("name");
        Betting betting = new Betting();
        assertThatCode(() -> betting.bet(player, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: 0 액수 배팅")
    void getProfitOf_Exception_ZeroBetting() {
        Player player = Player.nameOf("name");
        Betting betting = new Betting();
        assertThatCode(() -> betting.bet(player, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: unmodifiableBetting에 추가 배팅")
    void unmodifiable_Exception_additionalBet() {
        Betting betting = new Betting();
        Betting unmodifiableBetting = betting.unmodifiableBetting();

        assertThatCode(() -> unmodifiableBetting.bet(Player.nameOf("a"), 1))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("실패: unmodifiableBetting의 불변화")
    void unmodifiable_Exception_Make_Unmodifiable() {
        Betting betting = new Betting();
        Betting unmodifiableBetting = betting.unmodifiableBetting();

        assertThatCode(unmodifiableBetting::unmodifiableBetting)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
