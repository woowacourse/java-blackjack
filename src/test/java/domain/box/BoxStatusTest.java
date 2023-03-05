package domain.box;

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
            BoxStatus boxStatus = new BoxStatus(PlayResult.NOT_BUST, 10);
            Assertions.assertThat(boxStatus.isOnTurn()).isTrue();
        }

        @DisplayName("불가(스탠드)")
        @Test
        void isOnTurnStand() {
            BoxStatus boxStatus = new BoxStatus(PlayResult.STAND, 10);
            Assertions.assertThat(boxStatus.isOnTurn()).isFalse();
        }

        @DisplayName("불가(블랙잭)")
        @Test
        void isOnTurnBlackJack() {
            BoxStatus boxStatus = new BoxStatus(PlayResult.BLACK_JACK, 10);
            Assertions.assertThat(boxStatus.isOnTurn()).isFalse();
        }

        @DisplayName("불가(버스트)")
        @Test
        void isOnTurnBust() {
            BoxStatus boxStatus = new BoxStatus(PlayResult.BUST, 10);
            Assertions.assertThat(boxStatus.isOnTurn()).isFalse();
        }
    }

}
