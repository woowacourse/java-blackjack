package blackjack.domain.game;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class BettingTest {

    @Test
    @DisplayName("성공: 양수 액수 배팅")
    void getProfitOf_NoException_PlusBetting() {
        Player player1 = Player.nameOf("name");
        assertThatCode(() -> Betting.of(List.of(player1), player -> 1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: 음수 액수 배팅")
    void getProfitOf_Exception_MinusBetting() {
        Player player1 = Player.nameOf("name");
        assertThatCode(() -> Betting.of(List.of(player1), player -> -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: 0 액수 배팅")
    void getProfitOf_Exception_ZeroBetting() {
        Player player1 = Player.nameOf("name");
        assertThatCode(() -> Betting.of(List.of(player1), player -> 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
