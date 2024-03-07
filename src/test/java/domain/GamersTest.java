package domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {

    @DisplayName("게이머들에서 딜러를 찾는다.")
    @Test
    void findDealerTest(){
        // given
        Dealer dealer = new Dealer();

        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Player pobi = new Player(name1);
        Player jason = new Player(name2);

        Players players = new Players(List.of(pobi, jason));
        Gamers gamers = Gamers.of(players, dealer);

        // when
        Dealer result = gamers.findDealer();

        // then
        assertThat(result.getName()).isEqualTo(dealer.getName());
    }

    @DisplayName("게이머들에서 플레이어들을 찾는다.")
    @Test
    void findPlayerTest(){
        // given
        Dealer dealer = new Dealer();

        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Player pobi = new Player(name1);
        Player jason = new Player(name2);

        Players players = new Players(List.of(pobi, jason));
        Gamers gamers = Gamers.of(players, dealer);

        // when
        List<Player> results = gamers.findPlayers();

        // then
        assertThat(results).containsExactly(pobi, jason);
    }
}
