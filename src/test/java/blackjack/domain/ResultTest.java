package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("딜러와 플레이어들을 비교해 플레이어들의 승패 여부를 구할 수 있다.")
    @Test
    void result() {
        Dealer dealer = new Dealer();
        Player player1 = new Player("choonsik", 10000);
        Player player2 = new Player("papi", 5000);
        Players players = new Players(Arrays.asList(player1, player2));
        dealer.hit(new Card(Denomination.EIGHT, Suit.SPADE));
        dealer.hit(new Card(Denomination.JACK, Suit.CLOVER));
        player1.hit(new Card(Denomination.QUEEN, Suit.DIAMOND));
        player1.hit(new Card(Denomination.KING, Suit.HEART));
        player2.hit(new Card(Denomination.NINE, Suit.SPADE));
        player2.hit(new Card(Denomination.EIGHT, Suit.HEART));

        Map<Player, Status> expected = new LinkedHashMap<Player, Status>() {{
            put(player1, Status.WIN);
            put(player2, Status.LOSE);
        }};

        Result result = new Result(dealer, players);
        assertThat(result.getResult()).isEqualTo(expected);
    }
}
