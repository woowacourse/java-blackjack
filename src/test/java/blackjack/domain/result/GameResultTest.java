package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class GameResultTest {
    private GameResult gameResult;
    private Dealer dealer;
    private Player player;
    private List<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("bada", 10000);
        players.add(player);
        player.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        player.receiveOneCard(new Card(CardNumber.JACK, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.JACK, CardType.DIAMOND));
        dealer.receiveOneCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        gameResult = new GameResult(dealer, players);
    }

    @Test
    @DisplayName("GameResult가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new GameResult(dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어들의 수익계산을 잘 하는지 확인")
    void calculatePlayersProfit() {
        Map<Player, Integer> expected = new HashMap<>();
        expected.put(player, 15000);
        assertThat(gameResult.getPlayersProfit()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 수익계산을 잘 하는지 확인")
    void calculateDealerProfit() {
        assertThat(gameResult.getDealerProfit()).isEqualTo(-15000);
    }
}
