package domain.player;

import domain.participant.HandCards;
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
    void playerGroup_정상_생성_테스트() {
        List<Player> players = new ArrayList<>(List
                .of(new Player(new Name("pobi"), new HandCards()), new Player(new Name("Jason"), new HandCards()),
                        new Player(new Name("파도"), new HandCards()), new Player(new Name("이안"), new HandCards())));
        PlayerGroups playerGroups = new PlayerGroups(players);

        assertThat(playerGroups.getPlayerGroupSize()).isEqualTo(players.size());
    }

    @Test
    void 플레이어_정원초과_예외_테스트() {
        List<Player> players = new ArrayList<>(List
                .of(new Player(new Name("pobi"), new HandCards()), new Player(new Name("Jason"), new HandCards()),
                        new Player(new Name("파도"), new HandCards()), new Player(new Name("이안"), new HandCards()), new Player(new Name("슈크림"), new HandCards())));

        assertThatThrownBy(() -> new PlayerGroups(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정원");
    }

    @Test
    void 플레이어_중복이름_예외_테스트() {
        List<Player> players = new ArrayList<>(List
                .of(new Player(new Name("pobi"), new HandCards()), new Player(new Name("Jason"), new HandCards()),
                        new Player(new Name("파도"), new HandCards()), new Player(new Name("파도"), new HandCards())));

        assertThatThrownBy(() -> new PlayerGroups(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("동명이인");
    }
}