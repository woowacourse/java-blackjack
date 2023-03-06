package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {


    @DisplayName("플레이어와 딜러가 둘 다 버스트가 아니고, 플레이어의 점수가 더 크면 이긴다")
    @Test
    void player_dealer_neither_bust_WIN() {
        GameResult gameResult = GameResult.comparePlayerWithDealer(13, 10);
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어만 버스트이면 진다")
    @Test
    void player_bust_LOSE() {
        GameResult gameResult = GameResult.comparePlayerWithDealer(23, 10);
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어와 딜러가 둘 다 버스트이면 비긴다")
    @Test
    void player_dealer_both_bust_PUSH() {
        GameResult gameResult = GameResult.comparePlayerWithDealer(25, 23);
        assertThat(gameResult).isEqualTo(GameResult.PUSH);
    }
}
