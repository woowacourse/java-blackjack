package domain.player;

import domain.participant.player.Player;
import domain.participant.player.PlayerGroups;
import domain.vo.Name;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerGroupsTest {
    @Test
    void playerGroup_정상_생성_테스트(){
        List<Player> players = new ArrayList<>(List
                .of(new Player(new Name("pobi")), new Player(new Name("Jason")),
                        new Player(new Name("파도")), new Player(new Name("이안"))));
        PlayerGroups playerGroups = new PlayerGroups(players);

        assertThat(playerGroups.getPlayerGroupSize()).isEqualTo(players.size());
    }

    @Test
    void 플레이어_정원초과_테스트() {
        List<Player> players = new ArrayList<>(List
                .of(new Player(new Name("pobi")), new Player(new Name("Jason")),
                        new Player(new Name("파도")), new Player(new Name("이안")), new Player(new Name("슈크림"))));

        assertThatThrownBy(() -> new PlayerGroups(players))
                .isInstanceOf(IllegalArgumentException.class);
    }
}