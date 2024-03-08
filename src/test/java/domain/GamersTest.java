package domain;


import static org.assertj.core.api.Assertions.assertThat;

import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {
    Players players;
    Gamers gamers;

    @BeforeEach
    void init() {
        Dealer dealer = new Dealer();

        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Player pobi = new Player(name1);
        Player jason = new Player(name2);

        players = new Players(List.of(pobi, jason));
        gamers = Gamers.of(players, dealer);
    }

    @DisplayName("게이머들에서 딜러를 찾는다.")
    @Test
    void findDealerTest() {
        // given
        Name expectedName = new Name("딜러");

        // when
        Dealer result = gamers.findDealer();

        // then
        assertThat(result.getName()).isEqualTo(expectedName);
    }

    @DisplayName("게이머들에서 플레이어들을 찾는다.")
    @Test
    void findPlayerTest() {
        // when
        List<Player> results = gamers.findPlayers();

        // then
        assertThat(results).containsAll(players.getPlayers());
    }
}
