import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import player.Players;

class BlackjackGameTest {
    BlackjackGame blackjackGame;
    Players players;

    @BeforeEach
    void setUp() {
        players = new Players();
        blackjackGame = new BlackjackGame(players);
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가할 수 있다")
    void addPlayer() {
        blackjackGame.addPlayer("폴로");
        blackjackGame.addPlayer("로지");

        assertThat(players.count()).isEqualTo(2);
    }
}
