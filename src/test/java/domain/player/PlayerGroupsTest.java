package domain.player;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerGroupsTest {

    private final List<String> VALID_PLAYER_NAMES = List.of("Eian", "pado", "jason", "pobi");

    @Test
    void playerGroup_정상_생성_테스트(){
        PlayerGroups playerGroups = new PlayerGroups(VALID_PLAYER_NAMES);

        assertThat(playerGroups.getPlayerGroupSize()).isEqualTo(VALID_PLAYER_NAMES.size());
    }

    @Test
    void 플레이어_정원초과_테스트() {
        List<String> playerNames = new ArrayList<>(VALID_PLAYER_NAMES);
        playerNames.add("woowa");
        playerNames.add("kim");

        assertThatThrownBy(() -> new PlayerGroups(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 최대 플레이어 인원은");
    }
}
