package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Nested
    @DisplayName("플레이어 리스트 생성 테스트")
    class 플레이어_리스트_생성_테스트 {
        
        @Test
        @DisplayName("정상 테스트")
        void 정상_테스트() {
            List<Player> playerList = List.of(
                new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "jason"));

            assertDoesNotThrow(() -> new Players(playerList));
        }

        @Test
        @DisplayName("플레이어 인원 수가 1명 미만인 경우")
        void 플레이어_인원_수가_1명_미만인_경우() {
            List<Player> playerList = List.of();

            assertThrows(IllegalArgumentException.class, () -> new Players(playerList));
        }
        
        @Test
        @DisplayName("플레이어 인원 수가 7명 초과인 경우")
        void 플레이어_인원_수가_7명_초과인_경우() {
            List<Player> playerList = List.of(
                new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "jason"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "pion"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "kien"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "lion"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "brown"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "woni"),
                new Player(new Hand(new ArrayList<>()), Status.HIT, "ifff"));

            assertThrows(IllegalArgumentException.class, () -> new Players(playerList));
        }
    }

}
