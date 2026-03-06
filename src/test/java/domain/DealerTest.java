package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Status;
import blackjack.domain.Trump;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러 pitch 테스트")
    void 딜러_피치_테스트() {
        Trump trump = new Trump();
        Dealer dealer = new Dealer(new Hand(), Status.HIT, trump);
        List<Player> players = List.of(
            new Player(new Hand(), Status.HIT, "pobi"),
            new Player(new Hand(), Status.HIT, "jason")
        );
        int expected = 13 * 4 - (players.size() + 1) * 2;

        dealer.pitch(players);

        assertThat(trump).extracting("deck")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expected);
    }

}
