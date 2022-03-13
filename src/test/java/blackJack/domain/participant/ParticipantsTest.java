package blackJack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantsTest {

    @Test
    @DisplayName("플레이어의 이름이 중복된 경우 테스트")
    void checkDuplicatePlayerName() {
        List<Player> players = List.of(new Player("rookie"), new Player("rookie"));

        assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 1-7명이 아닌 경우 테스트")
    void checkPlayerCount() {
        List<Player> players = List.of(
                new Player("k1"), new Player("k2"), new Player("k3"), new Player("k4"),
                new Player("k5"), new Player("k6"), new Player("k7"), new Player("k8"));

        assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 인원수는 1명 이상 7명 이하여야 합니다.");
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        Player player1 = new Player("kei");
        Player player2 = new Player("rookie");
        Participants participants = new Participants(new Dealer(), List.of(player1, player2));

        participants.firstCardDispensing();

        assertThat(player1.getCards().size()).isEqualTo(2);
    }
}
