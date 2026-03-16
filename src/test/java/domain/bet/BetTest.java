package domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetTest {

    private Name firstPlayer;
    private Name secondPlayer;
    private Bet bet;

    @BeforeEach
    void set_up() {
        firstPlayer = new Name("피즈");
        secondPlayer = new Name("스타크");
        bet = new Bet(List.of(firstPlayer, secondPlayer));
    }

    @DisplayName("배팅한 금액이 수익에 반영된다.")
    @Test
    void 배팅_기록으로_결과_계산() {
        // given
        bet.bettingMoney(firstPlayer, 10_000);
        // when
        BetProfit betProfit = bet.calculateProfit(Map.of(firstPlayer, GameResult.WIN));
        Map<Name, Profit> playerBetProfit = betProfit.getPlayerBetProfit();
        // then
        assertThat(playerBetProfit.get(firstPlayer)).isEqualTo(new Profit(10_000));
    }

    @DisplayName("여러 플레이어의 배팅 기록으로 플레이어와 딜러의 수익을 계산한다.")
    @Test
    void 여러_플레이어_배팅_기록으로_모든_참여자_수익_계산() {
        // given
        bet.bettingMoney(firstPlayer, 10_000);
        bet.bettingMoney(secondPlayer, 20_000);
        // when
        BetProfit betProfit = bet.calculateProfit(Map.of(
                firstPlayer, GameResult.WIN,
                secondPlayer, GameResult.LOSE
        ));
        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(betProfit.getPlayerBetProfit().get(firstPlayer)).isEqualTo(new Profit(10_000));
            softAssertions.assertThat(betProfit.getPlayerBetProfit().get(secondPlayer)).isEqualTo(new Profit(-20_000));
            softAssertions.assertThat(betProfit.getDealerBetProfit()).isEqualTo(new Profit(10_000));
        });
    }
}
