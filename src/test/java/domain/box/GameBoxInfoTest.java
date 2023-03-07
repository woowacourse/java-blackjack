package domain.box;

import domain.user.PlayerStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameBoxInfoTest {

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

    @Nested
    class Equals {

        @DisplayName("동등할 때")
        @Test
        void same() {
            GameBoxInfo gameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 10);
            GameBoxInfo copyedGameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 10);
            Assertions.assertThat(gameBoxInfo.equals(copyedGameBoxInfo)).isTrue();
        }

        @DisplayName("다를 때")
        @Test
        void different() {
            GameBoxInfo gameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 10);
            GameBoxInfo differentGameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 11);
            Assertions.assertThat(gameBoxInfo.equals(differentGameBoxInfo)).isFalse();
        }
    }

    @Nested
    class Compare {

        @DisplayName("동등할 때")
        @Test
        void same() {
            GameBoxInfo firstGameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 10);
            GameBoxInfo secondGameBoxInfo = new GameBoxInfo(PlayerStatus.BUST, 10);
            Assertions.assertThat(firstGameBoxInfo.compareTo(secondGameBoxInfo)).isEqualTo(0);
        }

        @DisplayName("높을 때")
        @Test
        void higher() {
            GameBoxInfo firstGameBoxInfo = new GameBoxInfo(PlayerStatus.STAND, 11);
            GameBoxInfo secondGameBoxInfo = new GameBoxInfo(PlayerStatus.STAND, 10);
            Assertions.assertThat(firstGameBoxInfo.compareTo(secondGameBoxInfo)).isEqualTo(1);
        }

        @DisplayName("낮을 때")
        @Test
        void lower() {
            GameBoxInfo firstGameBoxInfo = new GameBoxInfo(PlayerStatus.STAND, 10);
            GameBoxInfo secondGameBoxInfo = new GameBoxInfo(PlayerStatus.STAND, 11);
            Assertions.assertThat(firstGameBoxInfo.compareTo(secondGameBoxInfo)).isEqualTo(-1);
        }
    }
}
