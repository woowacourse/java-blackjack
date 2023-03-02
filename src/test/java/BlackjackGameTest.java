import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame();
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가할 수 있다")
    void addPlayer() {

        blackjackGame.addPlayer("로지");
    }
}
