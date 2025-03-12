package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("문자열로부터 player 객체를 추가한다.")
    @Test
    void addPlayersFromString() {
        // given
        final Players players = new Players();
        final String names = "엠제이, 포비, 저스틴";

        // when
        players.addPlayersFrom(names);

        // then
        assertThat(players.getPlayers()).hasSize(3);
    }

    @DisplayName("빈 문자열로부터 player 객체들을 추가하려고 하면 예외를 발생시킨다.")
    @Test
    void addPlayersFromEmptyString() {
        // given
        final Players players = new Players();
        final String names = "";

        // when && then
        assertThatCode(() -> players.addPlayersFrom(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비어있는 값을 입력했습니다. 다시 입력해주세요.");
    }

    @DisplayName("입력한 닉네임의 개수가 6개를 초과하며 예외를 발생시킨다.")
    @Test
    void addPlayersFromOverSixSNametring() {
        // given
        final Players players = new Players();
        final String names = "엠제이, 칼리, 폰트, 띠용, 강산, 저스틴, 포비";

        // when && then
        assertThatCode(() -> players.addPlayersFrom(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적정 인원을 초과했습니다. 다시 입력해주세요.");
    }
}
