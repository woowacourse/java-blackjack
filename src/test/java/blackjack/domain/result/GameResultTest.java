package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    @DisplayName("플레이어들의 수익계산을 잘 하는지 확인")
    void calculatePlayersProfit() {
        Map<Player, Integer> expected = new HashMap<>();
        expected.put(player, 15000);
        assertThat(gameResult.calculatePlayersProfit(dealer)).isEqualTo(expected);
    }

    @Test
    @DisplayName("GameResult가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new GameResult(dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어의 승패를 잘 결정하는지 확인")
    void playersResult() {
        final Map<Player, Result> expected = new HashMap<>();
        expected.put(player, Result.WIN);
        assertThat(gameResult.getPlayersResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("승, 패, 무를 잘 카운트하는지 확인")
    void resultCounts() {
        final List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 0, 0));
        assertThat(gameResult.getResultCounts()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 수익계산을 잘 하는지 확인")
    void calculateDealerProfit() {
        Map<Player, Integer> playersProfit = new HashMap<>();
        playersProfit.put(player, 10000);
        playersProfit.put(new Player("pobi", 10000), 15000);
        assertThat(gameResult.calculateDealerProfit(playersProfit)).isEqualTo(-25000);
    }
}
