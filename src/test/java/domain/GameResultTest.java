package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {


    @DisplayName("플레이어와 딜러가 둘 다 버스트가 아니고, 플레이어의 점수가 더 크면 이긴다")
    @Test
    void player_dealer_neither_bust_WIN() {
        GameResult.comparePlayerWithDealer(13, 10);
    }

    @DisplayName("플레이어만 버스트이면 진다")
    @Test
    void player_bust_LOSE() {
        GameResult.comparePlayerWithDealer(23, 10);
    }

    @DisplayName("플레이어와 딜러가 둘 다 버스트이면 비긴다")
    @Test
    void player_dealer_both_bust_PUSH() {
        GameResult.comparePlayerWithDealer(25, 23);
    }
}
