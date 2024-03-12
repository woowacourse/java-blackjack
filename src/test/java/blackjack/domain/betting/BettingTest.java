package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Result;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BettingTest {

    private static final String DEFAULT_NAME = "name";
    private static final Money BETTING_MONEY = new Money(10000);

    @DisplayName("베팅 결과 상금 테스트")
    @Nested
    class PrizeTest {
        private Betting betting;

        @BeforeEach
        void createBetting() {
            betting = new Betting();
        }

        @DisplayName("게임 결과가 플레이어_블랙잭이라면 베팅 금액의 1.5배를 받는다.")
        @Test
        void playerBlackjackTest() {
            //given
            Player player = Player.from(DEFAULT_NAME);
            betting.bet(player, BETTING_MONEY);

            //when
            Money prize = betting.getPrize(Result.PLAYER_BLACKJACK, player);

            //then
            assertThat(prize).isEqualTo(BETTING_MONEY.multiply(1.5));
        }

        @DisplayName("베팅 결과가 플레이어 일반승 이라면 베팅 금액의 1.5배를 받는다.")
        @Test
        void playerWinTest() {
            //given
            Player player = Player.from(DEFAULT_NAME);
            betting.bet(player, BETTING_MONEY);

            //when
            Money prize = betting.getPrize(Result.PLAYER_WIN, player);

            //then
            assertThat(prize).isEqualTo(BETTING_MONEY.multiply(1));
        }

        @DisplayName("게임 결과가 무승부(PUSH) 라면 베팅 금액을 돌려받는다.")
        @Test
        void pushTest() {
            //given
            Player player = Player.from(DEFAULT_NAME);
            betting.bet(player, BETTING_MONEY);

            //when
            Money prize = betting.getPrize(Result.PUSH, player);

            //then
            assertThat(prize).isEqualTo(Money.ZERO);
        }

        @DisplayName("게임 결과가 플레이어 패배라면 베팅 금액을 잃는다.")
        @Test
        void playerLoseTest() {
            //given
            Player player = Player.from(DEFAULT_NAME);
            betting.bet(player, BETTING_MONEY);

            //when
            Money prize = betting.getPrize(Result.PLAYER_LOSE, player);

            //then
            assertThat(prize).isEqualTo(BETTING_MONEY.multiply(-1));
        }
    }
}
