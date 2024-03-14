package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.PlayerResult;
import blackjack.domain.game.result.PlayerBlackjack;
import blackjack.domain.game.result.PlayerLose;
import blackjack.domain.game.result.PlayerWin;
import blackjack.domain.game.result.Push;
import blackjack.domain.participant.Name;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BetsTest {

    private static final Name DEFAULT_NAME = new Name("name");
    private static final Money BETTING_MONEY = new Money(10000);

    @DisplayName("베팅 결과 상금 테스트")
    @Nested
    class PrizeTest {
        private final Bets bets = new Bets(List.of(new OwnedMoney(DEFAULT_NAME, BETTING_MONEY)));

        @DisplayName("게임 결과가 플레이어_블랙잭이라면 베팅 금액의 1.5배를 받는다.")
        @Test
        void playerBlackjackTest() {
            //given
            PlayerResult result = new PlayerResult(DEFAULT_NAME,
                    PlayerBlackjack.getInstance());

            //when
            OwnedMoney prize = bets.getPrize(result);

            //then
            assertThat(prize.getMoney()).isEqualTo(BETTING_MONEY.multiply(1.5));
        }

        @DisplayName("베팅 결과가 플레이어 일반승 이라면 베팅 금액의 1.5배를 받는다.")
        @Test
        void playerWinTest() {
            //given
            PlayerResult result = new PlayerResult(DEFAULT_NAME,
                    PlayerWin.getInstance());

            //when
            OwnedMoney prize = bets.getPrize(result);

            //then
            assertThat(prize.getMoney()).isEqualTo(BETTING_MONEY.multiply(1));
        }

        @DisplayName("게임 결과가 무승부(PUSH) 라면 베팅 금액을 돌려받는다.")
        @Test
        void pushTest() {
            //given
            PlayerResult result = new PlayerResult(DEFAULT_NAME,
                    Push.getInstance());

            //when
            OwnedMoney prize = bets.getPrize(result);

            //then
            assertThat(prize.getMoney()).isEqualTo(Money.ZERO);
        }

        @DisplayName("게임 결과가 플레이어 패배라면 베팅 금액을 잃는다.")
        @Test
        void playerLoseTest() {
            //given
            PlayerResult result = new PlayerResult(DEFAULT_NAME,
                    PlayerLose.getInstance());

            //when
            OwnedMoney prize = bets.getPrize(result);

            //then
            assertThat(prize.getMoney()).isEqualTo(BETTING_MONEY.multiply(-1));
        }
    }
}
