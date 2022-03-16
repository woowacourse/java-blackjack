package blackJack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantsTest {

    @Test
    @DisplayName("플레이어의 이름이 중복된 경우 테스트")
    void checkDuplicatePlayerName() {
        List<Player> players = List.of(new Player("rookie", "1000"), new Player("rookie", "1000"));

        assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 1-7명이 아닌 경우 테스트")
    void checkPlayerCount() {
        List<Player> players = List.of(
                new Player("k1", "10"), new Player("k2", "10"),
                new Player("k3", "10"), new Player("k4", "10"),
                new Player("k5", "10"), new Player("k6", "10"),
                new Player("k7", "10"), new Player("k8", "10"));

        assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 인원수는 1명 이상 7명 이하여야 합니다.");
    }
}
