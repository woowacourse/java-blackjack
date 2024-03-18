package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.vo.Card;
import blackjack.vo.PlayerName;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {
    @Test
    @DisplayName("플레이어 수는 1명 이상이 아니면 예외가 발생한다")
    void validatePlayersCountTest() {
        // when & then
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideDuplicatedNames")
    @DisplayName("플레이어 이름은 중복되면 예외가 발생한다")
    void validateDuplicatedNamesTest(List<PlayerName> names) {
        // when & then
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<PlayerName>> provideDuplicatedNames() {
        return Stream.of(
                List.of(new PlayerName("mia"), new PlayerName("mia"), new PlayerName("dora")),
                List.of(new PlayerName("dora"), new PlayerName("dora"), new PlayerName("dora"))
        );
    }

    @Test
    @DisplayName("모든 플레이어가 블랙잭이면 true를 반환한다")
    void isAllBlackJackTest() {
        // given
        Players players = new Players(List.of(new PlayerName("mia"), new PlayerName("dora")));
        players.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.ACE), new Card(Suit.HEART, Denomination.JACK),
                new Card(Suit.HEART, Denomination.ACE), new Card(Suit.HEART, Denomination.JACK)
        )));

        // when
        boolean actual = players.isAllBlackJack();

        // then
        assertThat(actual).isTrue();
    }
}
