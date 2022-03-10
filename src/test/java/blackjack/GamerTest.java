package blackjack;

import static blackjack.Rank.*;
import static blackjack.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.cards.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GamerTest {

    @Test
    @DisplayName("카드 발급 테스트")
    void playerCardTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(KING, SPADE));
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("카드 발급 실패 테스트")
    void playerCardCantTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(ACE, SPADE));
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("플레이어 첫 2장 카드 공개")
    void openCards() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
        assertThat(gamer.openCards()).hasSize(2);
        assertThat(gamer.openCards()).contains(new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
    }

    @ParameterizedTest
    @MethodSource("provideGamerInfo")
    @DisplayName("게이머 점수 반환 테스트")
    void gamerScore(Gamer gamer, int expect) {
        assertThat(gamer.score().getValue()).isEqualTo(expect);
    }

    protected static Stream<Arguments> provideGamerInfo() {
        return Stream.of(
                Arguments.of(new Gamer("player", new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(new Gamer("player", new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                        new Card(KING, CLOVER)), 21),
                Arguments.of(new Gamer("player", new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                        new Card(NINE, CLOVER)), 21),
                Arguments.of(new Gamer("player", new Card(QUEEN, CLOVER), new Card(JACK, HEART),
                        new Card(KING, DIAMOND)), 30),
                Arguments.of(new Gamer("player", new Card(THREE, DIAMOND), new Card(TWO, DIAMOND)), 5)
        );
    }
    // 게이머 카드 발급
    // 게이머 카드 발급 실패(예외)
}