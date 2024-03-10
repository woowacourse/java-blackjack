package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("블랙잭 플레이어 테스트")
class PlayerTest {

    @DisplayName("플레이어는 21이 넘으면 히트할 수 없다")
    @ParameterizedTest
    @CsvSource(value = {"2, 10, 10", "3, 10, 10", "4, 10, 10"})
    void testCannotHit(int card1, int card2, int card3) {
        Player player = new Player(new PlayerName("썬"), HandFixture.of(card1, card2, card3));
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("플레이어는 21이 넘으면 히트할 수 없다")
    @ParameterizedTest
    @CsvSource(value = {"1, 10, 10", "2, 8, 10", "2, 7, 10"})
    void testCanHit(int card1, int card2, int card3) {
        Player player = new Player(new PlayerName("썬"), HandFixture.of(card1, card2, card3));
        assertThat(player.canHit()).isTrue();
    }
}
