package blackjack.model.player;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.cards.Score;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card FOUR = new Card(Rank.FOUR, HEART);
    private static final Card FIVE = new Card(Rank.FIVE, CLOVER);
    private static final Card NINE = new Card(Rank.NINE, SPADE);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);
    private static final Name GAMER_NAME = new Name("pobi");

    @ParameterizedTest
    @MethodSource("providePlayers")
    @DisplayName("플레이어 점수 반환 테스트")
    void gamerScore(Player player, int expect) {
        assertThat(player.score().getValue()).isEqualTo(expect);
    }

    protected static Stream<Arguments> providePlayers() {
        return Stream.of(
            Arguments.of(new Player(GAMER_NAME, ACE, JACK), 21),
            Arguments.of(new Dealer(ACE, JACK, KING), 21),
            Arguments.of(new Dealer(ACE, ACE, NINE), 21),
            Arguments.of(new Player(GAMER_NAME, QUEEN, JACK, KING), 30),
            Arguments.of(new Player(GAMER_NAME, THREE, TWO), 5)
        );
    }

    @Test
    @DisplayName("게이머 첫 2장 카드 공개")
    void gamerOpenCards() {
        Player gamer = new Player(GAMER_NAME, QUEEN, FIVE);
        assertThat(gamer.openCards()).hasSize(2);
        assertThat(gamer.openCards()).contains(QUEEN, FIVE);
    }

    @Test
    @DisplayName("게이머 20이하일 경우 카드 발급 가능")
    void gamerCardTake() {
        Player gamer = new Player(GAMER_NAME, QUEEN, KING);
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("게이머 21이상일 경우 카드 발급 불가능")
    void gamerCardCantTake() {
        Player gamer = new Player(GAMER_NAME, QUEEN, ACE);
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("게이머 카드 발급")
    void gamerTakeCards() {
        Player dealer = new Player(GAMER_NAME, JACK, QUEEN);
        dealer.take(ACE);
        assertThat(dealer.score()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("게이머 카드 발급 실패")
    void gamerTakeInvalidCard() {
        Player dealer = new Player(GAMER_NAME, JACK, ACE);
        assertThatThrownBy(() -> dealer.take(FOUR))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게이머 버스트 확인")
    void isBust() {
        Player gamer = new Player(GAMER_NAME, TWO, QUEEN, KING);
        assertThat(gamer.isBust()).isTrue();
    }
}
