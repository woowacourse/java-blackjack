package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameTest {

    @Test
    @DisplayName("참가자의 이름이 중복되면 예외가 발생한다.")
    void nameDuplicate() {
        // give
        String name = "rick";

        // when
        final List<String> names = List.of(name, name);

        // then
        assertThatThrownBy(() -> new Game(CardFactory.createBy(new ArrayList<>()), names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복을 허용하지 않습니다.");

    }

    @ParameterizedTest
    @CsvSource(value = {"HIT:51", "STAY:52"}, delimiter = ':')
    @DisplayName("상태가 HIT이면 플레이어가 카드를 1장 뽑는다.")
    void drawCard_HIT(Status status, int expected) {
        // give
        final String name = "pobi";
        Game game = new Game(CardFactory.createNoShuffle(), List.of(name));
        final List<Player> players = game.getPlayers();

        // when
        game.progressPlayerTurn(players.get(0), status);
        final int actual = game.getRemainAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
