package domain.bet;

import static message.ErrorMessage.PLAYER_NOT_IN_BETTING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {

    private final Name firstPlayer = new Name("피즈");
    private final Name secondPlayer = new Name("스타크");

    @DisplayName("배팅 기록을 생성하면 플레이어별 금액이 저장된다.")
    @Test
    void 배팅_기록_생성_테스트() {
        Bet bet = new Bet(Map.of(
                firstPlayer, 10_000L,
                secondPlayer, 20_000L
        ));

        Map<Name, Money> betLog = bet.getBettingLog();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(betLog.get(firstPlayer)).isEqualTo(new Money(10_000));
            softAssertions.assertThat(betLog.get(secondPlayer)).isEqualTo(new Money(20_000));
        });
    }

    @DisplayName("배팅에 참여한 플레이어만 수익 계산이 가능하다.")
    @Test
    void 배팅에_참여하지_않은_플레이어의_수익은_계산할_수_없다() {
        Bet bet = new Bet(Map.of(firstPlayer, 10_000L));

        assertThatThrownBy(() -> bet.calculateProfit(Map.of(
                firstPlayer, GameResult.WIN,
                secondPlayer, GameResult.LOSE
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NOT_IN_BETTING.getMessage());
    }

    @DisplayName("배팅한 금액이 수익에 반영된다.")
    @Test
    void 배팅_기록으로_결과_계산() {
        Bet bet = new Bet(Map.of(firstPlayer, 10_000L));

        BetProfit betProfit = bet.calculateProfit(Map.of(firstPlayer, GameResult.WIN));
        Map<Name, Profit> playerBetProfit = betProfit.getPlayerBetProfit();

        assertThat(playerBetProfit.get(firstPlayer)).isEqualTo(new Profit(10_000));
    }

    @DisplayName("여러 플레이어의 배팅 기록으로 플레이어와 딜러의 수익을 계산한다.")
    @Test
    void 여러_플레이어_배팅_기록으로_모든_참여자_수익_계산() {
        Bet bet = new Bet(Map.of(
                firstPlayer, 10_000L,
                secondPlayer, 20_000L
        ));

        BetProfit betProfit = bet.calculateProfit(Map.of(
                firstPlayer, GameResult.WIN,
                secondPlayer, GameResult.LOSE
        ));

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(betProfit.getPlayerBetProfit().get(firstPlayer)).isEqualTo(new Profit(10_000));
            softAssertions.assertThat(betProfit.getPlayerBetProfit().get(secondPlayer)).isEqualTo(new Profit(-20_000));
            softAssertions.assertThat(betProfit.getDealerBetProfit()).isEqualTo(new Profit(10_000));
        });
    }
}
