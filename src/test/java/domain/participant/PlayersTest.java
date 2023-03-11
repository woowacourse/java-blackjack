package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Players를 정상적으로 생성한다.")
    @Test
    void create_success() {
        // given
        List<Player> expected = createPlayers("pobi", "neo", "ori", "jay");
        // when && then
        assertThatNoException()
                .isThrownBy(() -> new Players(expected));
    }

    @DisplayName("해당하는 이름과 일치하는 플레이어를 찾는다.")
    @Test
    void find_player_by_name() {
        //given
        Player expected = new Player(
                new Name("ori"), new DrawnCards(new ArrayList<>()), new BettingMoney(1000));
        List<Player> rawPlayers = createPlayers("pobi");
        rawPlayers.add(expected);
        Players players = new Players(rawPlayers);
        //when
        Player actual = players.findPlayerByName("ori");
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("이름에 해당하는 플레이어가 존재하지 않을 경우 예외를 반환한다.")
    @Test
    void find_not_contain_name_throw_exception() {
        //given
        List<Player> rawPlayers = createPlayers("pobi", "ori");
        Players players = new Players(rawPlayers);
        //when && then
        assertThatThrownBy(() -> players.findPlayerByName("failName"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어입니다.");
    }

    public List<Player> createPlayers(String... names) {
        List<Card> cards = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Name(name), new DrawnCards(cards),new BettingMoney(1000)) );
        }

        return players;
    }
}
