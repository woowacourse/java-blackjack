package blackjack.domain.gamer.player;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("플레이어들")
public class PlayersTest {
    @Test
    @DisplayName("은 여러명일 수 있다.")
    void playersOf() {
        // given
        List<String> names = List.of("pobi", "lemone", "seyang");

        // when
        Players players = Players.of(names, new Deck(cards -> cards));

        // then
        assertThat(players.getNames().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    "})
    @DisplayName("플레이어들이 없을 경우 예외가 발생한다.")
    void validateNoPlayers(String names) {
        // given & when & then
        assertThatCode(() -> Players.of(List.of(names), new Deck(cards -> cards)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }

    @Test
    @DisplayName("플레이어들의 이름이 중복될 경우 예외가 발생한다.")
    void validateDuplicate() {
        // given
        List<String> names = List.of("pobi", "pobi");
        // when & then
        assertThatCode(() -> Players.of(names, new Deck(cards -> cards)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }
}
