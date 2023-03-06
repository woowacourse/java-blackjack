package domain;

import domain.box.GameBoxInfo;
import domain.user.PlayerStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("다른 BoxResult의 값을 반대로 더한 BoxResult를 반환한다.")
    @Test
    void addReversed() {
        GameResult prevGameResult = new GameResult(0, 0);
        GameResult newGameResult = prevGameResult.addReversed(new GameResult(1, 3));
        Assertions.assertThat(newGameResult.getWinCount()).isEqualTo(3);
        Assertions.assertThat(newGameResult.getLoseCount()).isEqualTo(1);
    }

    @DisplayName("GameStatus의 compareTo()의 값으로 BoxResult를 생성한다.")
    @Nested
    class makeFromComparedResult {

        @DisplayName("비긴 경우")
        @Test
        void draw() {
            GameBoxInfo blackJack1 = new GameBoxInfo(PlayerStatus.BLACK_JACK, 21);
            GameBoxInfo blackJack2 = new GameBoxInfo(PlayerStatus.BLACK_JACK, 21);
            GameResult win = GameResult.from(blackJack1.compareTo(blackJack2));
            Assertions.assertThat(win.getWinCount()).isEqualTo(0);
            Assertions.assertThat(win.getLoseCount()).isEqualTo(0);
        }

        @DisplayName("이긴 경우")
        @Test
        void win() {
            GameBoxInfo blackJack = new GameBoxInfo(PlayerStatus.BLACK_JACK, 21);
            GameBoxInfo twenty = new GameBoxInfo(PlayerStatus.STAND, 20);
            GameResult win = GameResult.from(blackJack.compareTo(twenty));
            Assertions.assertThat(win.getWinCount()).isEqualTo(1);
            Assertions.assertThat(win.getLoseCount()).isEqualTo(0);
        }

        @DisplayName("진 경우")
        @Test
        void lose() {
            GameBoxInfo bust = new GameBoxInfo(PlayerStatus.BUST, 22);
            GameBoxInfo blackJack = new GameBoxInfo(PlayerStatus.BLACK_JACK, 21);
            GameResult win = GameResult.from(bust.compareTo(blackJack));
            Assertions.assertThat(win.getWinCount()).isEqualTo(0);
            Assertions.assertThat(win.getLoseCount()).isEqualTo(1);
        }
    }

}
