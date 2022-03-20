package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("플레이어의 이름 중 중복된 이름이 있을 경우 예외를 발생시킨다.")
    void checkDuplicatePlayerName() {
        assertThatThrownBy(() -> Participants.fromNames(List.of("rookie", "rookie")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 1 ~ 7 명이 아닌 경우 예외를 발생시킨다.")
    void checkPlayerCount() {
        List<String> names = List.of("k1", "k2", "k3", "k4", "k5", "k6", "k7", "k8");

        assertThatThrownBy(() -> Participants.fromNames(names))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 인원수는 1명 이상 7명 이하여야 합니다.");
    }
}
