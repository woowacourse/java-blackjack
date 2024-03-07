package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.dto.GameResult;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들")
public class PlayersTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private Players players;
    private final String nameA = "a";
    private final String nameB = "b";

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        players = Players.of(List.of(nameA, nameB), dealer);
    }

    @DisplayName("플레이어들의 승패를 계산한다.")
    @Test
    void decideResult() {
        //given & when
        GameResult gameResult = players.createResult(dealer);

        //then
        assertThat(gameResult.findPlayerResultByName(nameA)).isFalse();
        assertThat(gameResult.findPlayerResultByName(nameB)).isFalse();
        assertThat(gameResult.countWins()).isEqualTo(2);
        assertThat(gameResult.countLoses()).isEqualTo(0);
    }

    @DisplayName("버스트된 플레이어는 패배한다.")
    @Test
    void playerBurstLose() {
        //given
        Dealer burstedDealer = new Dealer(deck);
        Player player = players.getPlayers().get(0);

        //when
        IntStream.range(0, 12)
                .forEach(i -> player.draw(dealer));

        IntStream.range(0, 12)
                .forEach(i -> burstedDealer.draw());

        //then
        GameResult gameResult = players.createResult(dealer);
        assertThat(gameResult.findPlayerResultByName(nameA)).isFalse();

        GameResult burstedDealerGameResult = players.createResult(burstedDealer);
        assertThat(burstedDealerGameResult.findPlayerResultByName(nameA)).isFalse();
    }

    @DisplayName("딜러가 버스트 된 경우 버스트 안된 참가자는 승리한다.")
    @Test
    void dealerBurstPlayerWins() {
        //given
        Dealer burstedDealer = new Dealer(deck);

        //when
        IntStream.range(0, 12)
                .forEach(i -> burstedDealer.selfDraw());
        System.out.println(burstedDealer.getScore());
        System.out.println(burstedDealer.getHandCards());

        //then
        GameResult gameResult = players.createResult(burstedDealer);
        assertThat(gameResult.findPlayerResultByName(nameA)).isTrue();
    }
}
