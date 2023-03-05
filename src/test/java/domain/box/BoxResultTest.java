package domain.box;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoxResultTest {

    @DisplayName("다른 BoxResult의 값을 반대로 더한 BoxResult를 반환한다.")
    @Test
    void addReversed() {
        BoxResult prevResult = new BoxResult(0, 0);
        BoxResult newResult = prevResult.addReversed(new BoxResult(1, 3));
        Assertions.assertThat(newResult.getWinCount()).isEqualTo(3);
        Assertions.assertThat(newResult.getLoseCount()).isEqualTo(1);
    }

    @DisplayName("GameStatus의 compareTo()의 값으로 BoxResult를 생성한다.")
    @Nested
    class makeFromComparedResult {

        @DisplayName("비긴 경우")
        @Test
        void draw() {
            BoxStatus blackJack1 = new BoxStatus(PlayResult.BLACK_JACK, 21);
            BoxStatus blackJack2 = new BoxStatus(PlayResult.BLACK_JACK, 21);
            BoxResult win = BoxResult.from(blackJack1.compareTo(blackJack2));
            Assertions.assertThat(win.getWinCount()).isEqualTo(0);
            Assertions.assertThat(win.getLoseCount()).isEqualTo(0);
        }

        @DisplayName("이긴 경우")
        @Test
        void win() {
            BoxStatus blackJack = new BoxStatus(PlayResult.BLACK_JACK, 21);
            BoxStatus twenty = new BoxStatus(PlayResult.STAND, 20);
            BoxResult win = BoxResult.from(blackJack.compareTo(twenty));
            Assertions.assertThat(win.getWinCount()).isEqualTo(1);
            Assertions.assertThat(win.getLoseCount()).isEqualTo(0);
        }

        @DisplayName("진 경우")
        @Test
        void lose() {
            BoxStatus bust = new BoxStatus(PlayResult.BUST, 22);
            BoxStatus blackJack = new BoxStatus(PlayResult.BLACK_JACK, 21);
            BoxResult win = BoxResult.from(bust.compareTo(blackJack));
            Assertions.assertThat(win.getWinCount()).isEqualTo(0);
            Assertions.assertThat(win.getLoseCount()).isEqualTo(1);
        }
    }

}
