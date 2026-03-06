package controller;

import domain.game.Game;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackControllerTest {
    @Test
    @DisplayName("딜러와 참가자 초기_카드 나눠주기 기능 테스트")
    void 딜러_참가자_초기_카드_나눠주기_기능_테스트() {
        // given
        Game game = new Game(List.of("pobi", "jason"), 1);
        // when
        // then
    }
}