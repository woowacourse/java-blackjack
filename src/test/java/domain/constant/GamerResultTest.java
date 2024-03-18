package domain.constant;

import static domain.constant.GamerResult.BLACKJACK;
import static domain.constant.GamerResult.DRAW;
import static domain.constant.GamerResult.LOSE;
import static domain.constant.GamerResult.WIN;

import domain.dto.HandStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GamerResultTest {
    private static final HandStatus BUST_HAND = new HandStatus(22, 3);
    private static final HandStatus BLACKJACK_HAND = new HandStatus(21, 2);

    @Nested
    @DisplayName("Bust가 포함된 경우")
    class BustCase {
        @Test()
        @DisplayName("플레이어가 Bust이면 딜러가 승리한다")
        void playerIsBust() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            new HandStatus(16, 3),
                            BUST_HAND
                    ))
                    .isEqualTo(LOSE);
        }

        @Test()
        @DisplayName("딜러가 Bust이면 플레이어가 승리한다")
        void dealerIsBust() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            BUST_HAND,
                            new HandStatus(16, 3)
                    ))
                    .isEqualTo(WIN);
        }

        @Test()
        @DisplayName("플레이어와 딜러 모두 Bust이면 딜러가 승리한다")
        void bothAreBust() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            BUST_HAND,
                            BUST_HAND
                    ))
                    .isEqualTo(LOSE);
        }
    }

    @Nested
    @DisplayName("Bust가 포함되지 않은 경우")
    class NonBustCase {
        @Test()
        @DisplayName("딜러의 점수가 더 높으면 딜러가 승리한다")
        void dealerIsWin() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            new HandStatus(17, 3),
                            new HandStatus(16, 3)
                    ))
                    .isEqualTo(LOSE);
        }

        @Test()
        @DisplayName("플레이어의 점수가 더 높으면 플레이어가 승리한다")
        void playerIsWin() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            new HandStatus(16, 3),
                            new HandStatus(17, 3)
                    ))
                    .isEqualTo(WIN);
        }

        @Test()
        @DisplayName("둘의 점수가 같으면 무승부이다")
        void draw() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            new HandStatus(16, 3),
                            new HandStatus(16, 3)
                    ))
                    .isEqualTo(DRAW);
        }
    }

    @Nested
    @DisplayName("블랙잭이 포함된 경우")
    class BlackJackCase {
        @Test
        @DisplayName("플레이어만 블랙잭이다")
        void playerIsBlackJack() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            new HandStatus(16, 3),
                            BLACKJACK_HAND
                    ))
                    .isEqualTo(BLACKJACK);
        }

        @Test
        @DisplayName("딜러만 블랙잭이면 플레이어가 패배한다")
        void dealerIsBlackJack() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            BLACKJACK_HAND,
                            new HandStatus(16, 3)
                    ))
                    .isEqualTo(LOSE);
        }

        @Test
        @DisplayName("양쪽이 모두 블랙잭이면 무승부이다")
        void bothAreBlackJack() {
            Assertions.assertThat(GamerResult.judgePlayerResult(
                            BLACKJACK_HAND,
                            BLACKJACK_HAND
                    ))
                    .isEqualTo(DRAW);
        }
    }
}
