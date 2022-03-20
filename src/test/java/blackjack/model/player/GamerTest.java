package blackjack.model.player;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.cards.Score;
import blackjack.model.player.matcher.Money;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GamerTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card FOUR = new Card(Rank.FOUR, HEART);
    private static final Card FIVE = new Card(Rank.FIVE, CLOVER);
    private static final Card NINE = new Card(Rank.NINE, SPADE);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);
    private static final String GAMER_NAME = "pobi";
    private static final Money MONEY = new Money(new BigDecimal("1000"));

    @ParameterizedTest
    @MethodSource("providePlayers")
    @DisplayName("플레이어 점수 반환 테스트")
    void gamerScore(Gamer gamer, int expect) {
        assertThat(gamer.score().getValue()).isEqualTo(expect);
    }

    protected static Stream<Arguments> providePlayers() {
        return Stream.of(
            Arguments.of(new Gamer(GAMER_NAME, MONEY, ACE, JACK), 21),
            Arguments.of(new Gamer(GAMER_NAME, MONEY, ACE, JACK, KING), 21),
            Arguments.of(new Gamer(GAMER_NAME, MONEY, ACE, ACE, NINE), 21),
            Arguments.of(new Gamer(GAMER_NAME, MONEY, QUEEN, JACK, KING), 30),
            Arguments.of(new Gamer(GAMER_NAME, MONEY, THREE, TWO), 5)
        );
    }

    @Test
    @DisplayName("게이머 첫 2장 카드 공개")
    void gamerOpenCards() {
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, FIVE);
        assertThat(gamer.openCards().values()).hasSize(2);
        assertThat(gamer.openCards().values()).contains(QUEEN, FIVE);
    }

    @Test
    @DisplayName("게이머 20이하일 경우 카드 발급 가능")
    void gamerCardTake() {
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, KING);
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("게이머 21이상일 경우 카드 발급 불가능")
    void gamerCardCantTake() {
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, QUEEN, ACE);
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("게이머 카드 발급")
    void gamerTakeCards() {
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, JACK, QUEEN);
        gamer.take(ACE);
        assertThat(gamer.score()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("게이머 카드 발급 실패")
    void gamerTakeInvalidCard() {
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, JACK, ACE);
        assertThatThrownBy(() -> gamer.take(FOUR))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게이머 버스트 확인")
    void isBust() {
        Gamer gamer = new Gamer(GAMER_NAME, MONEY, TWO, QUEEN, KING);
        assertThat(gamer.isBust()).isTrue();
    }

}
