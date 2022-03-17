package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    private static final int TYPES_LENGTH = 4;
    private static final int DEFAULT_BETTING_AMOUNT = 1000;

    @DisplayName("애플리케이션 생성 시점에 BLACKJACK_WIN, WIN, LOSE, DRAW라는 4개의 인스턴스가 생성된다.")
    @Test
    void init() {
        DuelResult[] resultTypes = DuelResult.values();

        assertThat(resultTypes[0]).isEqualTo(DuelResult.valueOf("BLACKJACK_WIN"));
        assertThat(resultTypes[1]).isEqualTo(DuelResult.valueOf("WIN"));
        assertThat(resultTypes[2]).isEqualTo(DuelResult.valueOf("LOSE"));
        assertThat(resultTypes[3]).isEqualTo(DuelResult.valueOf("DRAW"));
        assertThat(resultTypes.length).isEqualTo(TYPES_LENGTH);
    }

    @DisplayName("getProfitOf 메서드 호출시, 배당금액에 현재 타입에 해당되는 배당률을 적용한 값을 반환한다.")
    @Nested
    class GetProfitOfTest {

        @DisplayName("블랙잭으로 승리하는 경우 베팅금액에 1.5배를 적용한 금액을 반환한다.")
        @Test
        void getProfitOf_blackjackWin() {
            int actual = DuelResult.BLACKJACK_WIN.getProfitOf(DEFAULT_BETTING_AMOUNT);
            int expected = DEFAULT_BETTING_AMOUNT * 3 / 2;

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("블랙잭으로 승리하는 경우 1.5배를 적용하는 과정에서 소수는 버린다.")
        @Test
        void getProfitOf_blackjackWinIgnoreDecimals() {
            int actual = DuelResult.BLACKJACK_WIN.getProfitOf(3);
            int expected = 4; // 4.5

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("블랙잭이 아닌 패로 승리하는 경우 배당금액을 그대로 반환한다.")
        @Test
        void getProfitOf_win() {
            int actual = DuelResult.WIN.getProfitOf(DEFAULT_BETTING_AMOUNT);

            assertThat(actual).isEqualTo(DEFAULT_BETTING_AMOUNT);
        }

        @DisplayName("패배한 경우 배당금액의 음수값을 반환한다.")
        @Test
        void getProfitOf_lose() {
            int actual = DuelResult.LOSE.getProfitOf(DEFAULT_BETTING_AMOUNT);
            int expected = DEFAULT_BETTING_AMOUNT * -1;

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("무승부인 경우 언제나 0을 반환한다.")
        @Test
        void getProfitOf_draw() {
            int actual = DuelResult.DRAW.getProfitOf(DEFAULT_BETTING_AMOUNT);
            int expected = 0;

            assertThat(actual).isEqualTo(expected);
        }
    }
}
