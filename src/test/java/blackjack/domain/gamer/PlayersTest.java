package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("참가자 인원이 2 ~ 8명이 아닌 경우 예외 처리 확인")
    void numberOfPlayersTest() {
        List<String> ninePlayerNames = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

        assertThatThrownBy(() -> new Players(ninePlayerNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("2명에서 8명 까지만 참가 가능합니다.");
    }

    @Test
    @DisplayName("참가자의 이름이 중복인 경우 확인")
    void duplicateTest() {
        List<String> names = List.of("a", "a");

        assertThatThrownBy(() -> new Players(names))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참가자 이름은 중복될 수 없습니다.");
    }
}
