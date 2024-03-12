package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Dealer;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.BlackjackResult;
import blackjack.dto.PlayerInfo;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들")
public class PlayersTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private Players players;
    private final String bettingAmountA = "10000";
    private final String bettingAmountB = "200000";
    private final PlayerInfo playerA = PlayerInfo.of("a", bettingAmountA);
    private final PlayerInfo playerB = PlayerInfo.of("b", bettingAmountB);

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        dealer.draw(2);
        players = Players.of(List.of(playerA, playerB));
        IntStream.range(0, 2)
                .forEach(i -> players.getPlayers()
                        .forEach(player -> player.draw(dealer.draw())));
    }

    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    @Test
    void validateDuplicatedNames() {
        //given
        List<PlayerInfo> playerInfos = List.of(
                PlayerInfo.of("choco", "10000"),
                PlayerInfo.of("choco", "20000"),
                PlayerInfo.of("chip", "9800")
        );

        //when & then
        assertThatThrownBy(() -> Players.of(playerInfos))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어들의 승패를 계산한다.")
    @Test
    void decideResult() {
        //given
        String expectedProfitSum = String.valueOf(Integer.parseInt(bettingAmountA) + Integer.parseInt(bettingAmountB));

        //when
        BlackjackResult blackjackResult = Judge.judge(dealer, players);

        //then
        assertThat(blackjackResult.playerProfits().get(0).profit()).isEqualTo("-" + bettingAmountA);
        assertThat(blackjackResult.playerProfits().get(1).profit()).isEqualTo("-" + bettingAmountB);
        assertThat(blackjackResult.dealerProfit()).isEqualTo(expectedProfitSum);
    }

    @DisplayName("딜러가 버스트 된 경우 버스트 안된 참가자는 승리한다.")
    @Test
    void dealerBustPlayerWins() {
        //given
        Dealer bustedDealer = new Dealer(deck);

        //when
        IntStream.range(0, 6)
                .forEach(i -> bustedDealer.requestExtraCard());

        //then
        BlackjackResult blackjackResult = Judge.judge(bustedDealer, players);
        assertThat(blackjackResult.playerProfits().get(0).profit()).isEqualTo(bettingAmountA);
    }
}
