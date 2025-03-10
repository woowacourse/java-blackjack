package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Map;
import model.cards.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어 이름을 통해 Cards를 찾는다.")
    @Test
    void test1() {
        Cards pobiCards = new Cards(new ArrayList<>());
        Players players = new Players(Map.of(
                "pobi", pobiCards,
                "jason", new Cards(new ArrayList<>())
        ));

        assertThat(players.findCardsByName("pobi")).isEqualTo(pobiCards);
    }

    @DisplayName("플레이어가 존재하지 않을 경우 예외가 발생한다.")
    @Test
    void test2() {
        Players players = new Players(Map.of(
                "pobi", new Cards(new ArrayList<>()),
                "jason", new Cards(new ArrayList<>())
        ));

        assertThatThrownBy(() -> players.findCardsByName("none"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
