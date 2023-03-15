package domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {
    
    @Test
    @DisplayName("게임 상태 생성 테스트 - WIN")
    void getGameStatusTest() {
        GameStatus gameStatus = GameStatus.of(true, false, false);
        Assertions.assertThat(gameStatus).isEqualTo(GameStatus.WIN);
        GameStatus gameStatus2 = GameStatus.of(true, true, false);
        Assertions.assertThat(gameStatus2).isEqualTo(GameStatus.WIN);
    }
    
    @Test
    @DisplayName("게임 상태 생성 테스트 - LOSE")
    void getGameStatusTest2() {
        GameStatus gameStatus = GameStatus.of(false, false, false);
        Assertions.assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }
    
    @Test
    @DisplayName("게임 상태 생성 테스트 - DRAW")
    void getGameStatusTest3() {
        GameStatus gameStatus = GameStatus.of(false, true, false);
        Assertions.assertThat(gameStatus).isEqualTo(GameStatus.DRAW);
    }
    
    @Test
    @DisplayName("게임 상태 생성 테스트 - WIN_BLACKJACK")
    void getGameStatusTest4() {
        GameStatus gameStatus = GameStatus.of(true, false, true);
        Assertions.assertThat(gameStatus).isEqualTo(GameStatus.WIN_BLACKJACK);
    }
}