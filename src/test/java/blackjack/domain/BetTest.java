package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.bet.Bet;
import blackjack.domain.participant.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BetTest {

    @Nested
    @DisplayName("생성 테스트")
    class 생성_테스트 {

        @Test
        @DisplayName("정상 테스트")
        void 정상_테스트() {
            int amount = 10000;

            assertDoesNotThrow(() -> new Bet(amount));
        }

        @Test
        @DisplayName("베팅금이 5,000 미만인 경우")
        void 베팅금이_5000_미만인_경우() {
            int amount = 1000;

            assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅금은 5,000 이상이어야 합니다.");
        }

        @Test
        @DisplayName("베팅금이 100,000,000 초과인 경우")
        void 베팅금이_100_000_000_초과인_경우() {
            int amount = 100_001_000;

            assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅금은 100,000,000 이하여야 합니다.");
        }

        @Test
        @DisplayName("베팅금 단위가 1,000이 아닌 경우")
        void 베팅금_단위가_1000이_아닌_경우() {
            int amount = 5001;

            assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅금은 1,000 단위여야 합니다.");
        }
    }
}
