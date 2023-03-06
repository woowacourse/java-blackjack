package domain.box;

import domain.user.PlayerStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoxStatusTest {

    @DisplayName("턴을 더 진행할 수 있는지 반환한다.")
    @Nested
    class isOnTurn {

        @DisplayName("가능(히트)")
        @Test
        void isOnTurnNotBust() {
            GameBoxInfo gameBoxInfo = new GameBoxInfo(PlayerStatus.HIT_ABLE, 10);
            Assertions.assertThat(gameBoxInfo.isOnTurn()).isTrue();
        }

        @DisplayName("불가(스탠드)")
        @Test
        void isOnTurnStand() {
            GameBoxInfo gameBoxInfo = new GameBoxInfo(PlayerStatus.STAND, 10);
            Assertions.assertThat(gameBoxInfo.isOnTurn()).isFalse();
        }

        @DisplayName("불가(블랙잭)")
        @Test
        void isOnTurnBlackJack() {
            GameBoxInfo gameBoxInfo = new GameBoxInfo(PlayerStatus.BLACK_JACK, 10);
            Assertions.assertThat(gameBoxInfo.isOnTurn()).isFalse();
        }

        @DisplayName("불가(버스트)")
        @Test
        void isOnTurnBust() {
            GameBoxInfo gameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 10);
            Assertions.assertThat(gameBoxInfo.isOnTurn()).isFalse();
        }
    }

}
