package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.dto.GameResult;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
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

    // a -> 13 승
    // b -> 7 패
    // dealer -> 11 1승 1패
}
