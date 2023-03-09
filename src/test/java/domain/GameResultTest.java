package domain;

import domain.participants.Player;
import domain.participants.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class  GameResultTest {
    @Test
    @DisplayName("GameResult 생성 성공 테스트")
    void createGameResultTest(){
        Assertions.assertThatNoException().isThrownBy(()->new GameResult(new Players(List.of("pobi",
                "ocean"))));
    }


}
