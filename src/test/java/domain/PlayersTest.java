package domain;

import static org.assertj.core.api.Assertions.assertThat;
import domain.participant.Players;
import dto.PlayerResultInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PlayersTest {
    @Test
    void 참가자_이름이_중복인_경우_예외를_발생한다() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자가_5인을_초과한_경우_예외를_발생한다() {
        List<String> names = List.of("pobi", "jason", "neo", "brown", "woni", "lisa");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어들의_총_수익을_계산한다() {
        Players players = new Players(List.of("pobi", "jason"));

        players.getPlayers().get(0).bet(new Money(10000));
        players.getPlayers().get(0).applyRoundResult(WinningStatus.WIN);
        players.getPlayers().get(1).bet(new Money(20000));
        players.getPlayers().get(1).applyRoundResult(WinningStatus.LOSE);

        assertThat(players.totalProfit()).isEqualTo(-10000);
    }

    @Test
    void 플레이어_결과_정보를_생성한다() {
        Players players = new Players(List.of("pobi", "jason"));

        players.getPlayers().get(0).bet(new Money(10000));
        players.getPlayers().get(0).applyRoundResult(WinningStatus.WIN);
        players.getPlayers().get(1).bet(new Money(20000));
        players.getPlayers().get(1).applyRoundResult(WinningStatus.LOSE);

        List<PlayerResultInfo> resultInfos = players.resultInfos();

        assertThat(resultInfos).containsExactly(
                new PlayerResultInfo("pobi", 10000),
                new PlayerResultInfo("jason", -20000)
        );
    }
}
