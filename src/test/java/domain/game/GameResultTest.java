package domain.game;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("게임 결과는 ")
class GameResultTest {
    @Test
    @DisplayName("계산되기 위해 GameResult 객체 생성을 할 수 있다.")
    void createTest() {
        assertDoesNotThrow(() -> new GameResult(List.of(new Name("pobi"), new Name("neo"))));
    }
}
