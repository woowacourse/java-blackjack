package blackjack.domain.participant;

import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @DisplayName("문자열을 받아 Player를 생성한다.")
    @Test
    void createPlayersWhenGivenString() {
        // given
        String input = "pobi,jason";

        // when
        Players players = Players.createPlayers(input);

        // then
        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @DisplayName("Player의 수가 1명 이상 7명 이하가 아니면 예외를 던진다.")
    @Test
    void validateNumberOfPlayers() {
        // given
        String input = "a,b,c,d,e,f,g,h";

        // when & then
        assertThatThrownBy(() -> Players.createPlayers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 1명 이상 7명 이하만 가능합나다.");
    }

    @DisplayName("Players에 중복된 Player의 이름이 있으면 예외를 던진다.")
    @Test
    void validateDuplicatedPlayers() {
        // given
        String input = "a,a,b,c";

        // when & then
        assertThatThrownBy(() -> Players.createPlayers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("각 플레이어는 중복된 이름을 가질 수 없습니다.");
    }
}
