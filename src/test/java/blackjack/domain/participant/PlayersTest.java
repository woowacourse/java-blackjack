package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("이름들을 입력받아서 플레이어들을 생성")
    void testCreatePlayers() {
        List<String> names = Arrays.asList("pobi", "brown", "jason");
        Players players = new Players(names);
        assertThat(players.toList()).hasSize(3);
    }

    @Test
    @DisplayName("중복된 이름 입력시 예외처리")
    void testDuplicateException() {
        List<String> names = Arrays.asList("pobi", "pobi", "jason");
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("NULL 혹은 공백을 입력했을 시 예외처리")
    void testNullOrBlankException() {
        List<String> blank1 = Collections.singletonList("");
        assertThatThrownBy(() -> new Players(blank1))
                .isInstanceOf(IllegalArgumentException.class);
        List<String> blank2 = Collections.singletonList(" ");
        assertThatThrownBy(() -> new Players(blank2))
                .isInstanceOf(IllegalArgumentException.class);
        List<String> nullList = null;
        assertThatThrownBy(() -> new Players(nullList))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        List<String> names = Arrays.asList("pobi", "jason");
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        players.initHandByDealer(dealer);
        assertThat(players.toList()
                .stream()
                .filter(player -> player.toHandList().size() == 2)
                .count())
                .isEqualTo(2);
    }
}