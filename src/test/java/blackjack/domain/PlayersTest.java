package blackjack.domain;

import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 참여자가_6명을_초과하면_예외를_던진다() {
        // given
        List<Player> players = List.of(
                new Player("히로", new Hand()),
                new Player("히포", new Hand()),
                new Player("모루", new Hand()),
                new Player("서프", new Hand()),
                new Player("웨이드", new Hand()),
                new Player("줄리", new Hand()),
                new Player("새로이", new Hand())
        );

        // when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자의_이름을_가져올_수_있다() {
        // given
        List<String> names = List.of("히로", "히포");
        List<Player> participantsToBeSaved = new java.util.ArrayList<>(names.stream()
                .map(name -> new Player(name, new Hand()))
                .toList());

        Players players = new Players(participantsToBeSaved);

        // when
        List<String> namesOfParticipants = players.getNamesOfParticipants();

        // then
        assertThat(namesOfParticipants).containsAll(names);
    }
}
