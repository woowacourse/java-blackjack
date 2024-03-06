package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.dto.GameResult;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들")
public class PlayersTest {

    @DisplayName("플레이어들의 승패를 계산한다.")
    @Test
    void decideResult() {
        //given
        ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);

        //when
        Players players = Players.of(List.of("a", "b"), deck);
        Dealer dealer = new Dealer("딜러", deck);

        GameResult gameResult = players.createResult(dealer);

        //then
        assertThat(gameResult.findPlayerResultByName("a")).isTrue();
        assertThat(gameResult.findPlayerResultByName("b")).isFalse();
        assertThat(gameResult.countWins()).isEqualTo(1);
        assertThat(gameResult.countLoses()).isEqualTo(1);
    }

    @DisplayName("버스트된 플레이어는 패배한다.")
    @Test
    void playerBurstLose() {
        //given
        ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);

        //when
        Players players = Players.of(List.of("a", "b"), deck);
        Dealer dealer = new Dealer("딜러", deck);
        Dealer burstedDealer = new Dealer("딜러", deck);

        Player player = players.getPlayers().get(0);
        IntStream.range(0, 12)
                .forEach(i -> player.draw(deck));

        IntStream.range(0, 12)
                .forEach(i -> burstedDealer.draw(deck));

        //then
        GameResult gameResult = players.createResult(dealer);
        assertThat(gameResult.findPlayerResultByName("a")).isFalse();

        GameResult burstedDealerGameResult = players.createResult(burstedDealer);
        assertThat(burstedDealerGameResult.findPlayerResultByName("a")).isFalse();
    }
}
