package domain.bet;

import static message.ErrorMessage.PLAYER_NOT_IN_GAME;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.participant.Name;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetHistoryTest {

    private Name firstPlayer;
    private Name secondPlayer;
    private BetHistory betHistory;

    @BeforeEach
    void set_up() {
        firstPlayer = new Name("피즈");
        secondPlayer = new Name("스타크");
        betHistory = new BetHistory(List.of(firstPlayer, secondPlayer));
    }

    @DisplayName("게임에 참여한 플레이어만 값을 배팅할 수 없다.")
    @Test
    void 플레이어_금액_배팅_테스트() {
        Name unknownPlayer = new Name("신원미상");

        assertThatThrownBy(() -> betHistory.bettingMoney(unknownPlayer, 10_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NOT_IN_GAME.getMessage());
    }

    @DisplayName("금액을 정상 배팅하는 경우의 기능을 테스트합니다.")
    @Test
    void 금액_배팅_기능_테스트() {
        //given
        //when
        betHistory.bettingMoney(firstPlayer, 10_000);
        betHistory.bettingMoney(secondPlayer, 20_000);
        //then
        Map<Name, Money> betLog = betHistory.getBetHistory();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(betLog.get(firstPlayer)).isEqualTo(Money.bet(10_000));
            softAssertions.assertThat(betLog.get(secondPlayer)).isEqualTo(Money.bet(20_000));
        });
    }

}
