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
    private final PlayerInfo playerA = PlayerInfo.of("a", "10000");

    private final PlayerInfo playerB = PlayerInfo.of("b", "200000");

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
        //given & when
        BlackjackResult blackjackResult = players.createResult(dealer);

        //then
        assertThat(blackjackResult.findPlayerResultByName(playerA.name())).isEqualTo(GameResult.LOSE);
        assertThat(blackjackResult.findPlayerResultByName(playerB.name())).isEqualTo(GameResult.LOSE);
        assertThat(blackjackResult.getDealerResult().getWins()).isEqualTo(2);
        assertThat(blackjackResult.getDealerResult().getLoses()).isEqualTo(0);
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
        BlackjackResult blackjackResult = players.createResult(bustedDealer);
        assertThat(blackjackResult.findPlayerResultByName(playerA.name())).isEqualTo(GameResult.WIN);
    }
}
