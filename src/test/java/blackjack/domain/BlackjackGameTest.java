package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackGameTest {
    @Test
    @DisplayName("블랙잭 게임을 관리하는 객체 생성 - 플레이어 이름을 받아 세팅")
    void createBlackjackGame() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");

        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        assertThat(blackjackGame).isInstanceOf(BlackjackGame.class);
    }
}
