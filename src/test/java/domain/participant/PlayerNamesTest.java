package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerNamesTest {
    @DisplayName("참여자의 이름은 중복될 수 없습니다.")
    @Test
    void createPlayerNamesFailTestByDuplication() {
        assertThatThrownBy(() -> PlayerNames.from(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "참여자 수가 0명이거나  8명보다 많으면 생성할 수 없습니다.")
    @ValueSource(ints = {0, 9})
    void createPlayerNamesFailTestByNumberOfPlayers(int count) {
        assertThatThrownBy(() -> PlayerNames.from(createPlayerNamesByCount(count)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "참여자 수는 1명 이상 8명 이하여야 합니다.")
    @ValueSource(ints = {1, 2, 6, 8})
    void createPlayerNamesSuccessTestByNumberOfPlayers(int count) {
        PlayerNames playerNames = PlayerNames.from(createPlayerNamesByCount(count));

        assertThat(playerNames.getNames())
                .hasSize(count);
    }

    private List<String> createPlayerNamesByCount(int count) {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            names.add(String.valueOf(i));
        }

        return names;
    }
}
