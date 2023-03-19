package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("입력된 참가자의 수가 2명 미만인 경우 예외 발생")
    void validateLength_under2() {
        // given
        Map<String, Integer> players = new LinkedHashMap<>() {{
            put("a", 1000);
        }};

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(players))
                .withMessage("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 참가자의 수가 8명 초과인 경우 예외 발생")
    void validateLength_over8() {
        // given
        Map<String, Integer> players = new LinkedHashMap<>() {{
            put("1", 1000);
            put("2", 1000);
            put("3", 1000);
            put("4", 1000);
            put("5", 1000);
            put("6", 1000);
            put("7", 1000);
            put("8", 1000);
            put("9", 1000);
        }};

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(players))
                .withMessage("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 참가자들로 이루어진 Players 객체 생성 확인")
    void createPlayers() {
        // given
        Map<String, Integer> playersMap = new LinkedHashMap<>() {{
            put("milli", 1000);
            put("doggy", 1000);
        }};

        // when
        Players players = Players.from(playersMap);

        // then
        assertAll(
                () -> assertThat(players.getPlayers().get(0).getName()).isEqualTo("milli"),
                () -> assertThat(players.getPlayers().get(1).getName()).isEqualTo("doggy")
        );
    }
}
