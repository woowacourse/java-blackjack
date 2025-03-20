package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        players = providePlayers();
    }

    @DisplayName("이름들로 Player 객체를 생성한다.")
    @Test
    void createPlayers() {
        // given
        List<Player> players = List.of(new Player(provideEmptyCards(), "엠제이", BigDecimal.valueOf(10_000)),
                new Player(provideEmptyCards(), "밍트", BigDecimal.valueOf(20_000)));

        // when & then
        Assertions.assertThatCode(() -> new Players(players))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복된 이름의 경우 예외를 발생한다.")
    @Test
    void createDuplicatePlayers() {
        // given
        List<Player> players = List.of(new Player(provideEmptyCards(), "엠제이", BigDecimal.valueOf(10_000)),
                new Player(provideEmptyCards(), "엠제이", BigDecimal.valueOf(20_000)));

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 중복된 이름을 입력했습니다.");
    }

    @DisplayName("플레이어들이 카드를 받는다.")
    @Test
    void receiveCardsByCount() {
        // given
        final Hand hand = provideCards(4);
        final int count = 2;

        // when
        players.receiveCardsByCount(hand, count);

        // then
        assertAll(
                () -> assertThat(players.getPlayers().getFirst()).isEqualTo(
                        new Player(hand.subHand(0, 2), "엠제이", BigDecimal.valueOf(10_000))),
                () -> assertThat(players.getPlayers().get(1)).isEqualTo(
                        new Player(hand.subHand(2, 4), "밍트", BigDecimal.valueOf(20_000)))
        );
    }
}
