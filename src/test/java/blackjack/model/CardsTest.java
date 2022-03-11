package blackjack.model;

import static blackjack.model.Rank.ACE;
import static blackjack.model.Rank.EIGHT;
import static blackjack.model.Rank.JACK;
import static blackjack.model.Rank.KING;
import static blackjack.model.Rank.NINE;
import static blackjack.model.Rank.QUEEN;
import static blackjack.model.Rank.THREE;
import static blackjack.model.Rank.TWO;
import static blackjack.model.Suit.CLOVER;
import static blackjack.model.Suit.DIAMOND;
import static blackjack.model.Suit.HEART;
import static blackjack.model.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card NINE = new Card(Rank.NINE, SPADE);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("최고의 카드 점수 계산")
    void bestScore(Cards cards, int expect) {
        assertThat(cards.bestScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(new Cards(ACE, JACK), 21),
                Arguments.of(new Cards(ACE, JACK, KING), 21),
                Arguments.of(new Cards(ACE, ACE, ACE, ACE), 14),
                Arguments.of(new Cards(QUEEN, JACK, KING), 30),
                Arguments.of(new Cards(THREE, TWO), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaxCards")
    @DisplayName("최대 카드 점수 계산")
    void maxScore(Cards cards, int expect) {
        assertThat(cards.maxScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideMaxCards() {
        return Stream.of(
                Arguments.of(new Cards(ACE, JACK), 21),
                Arguments.of(new Cards(ACE, JACK, KING), 31),
                Arguments.of(new Cards(ACE, ACE, NINE), 31)
        );
    }

    @Test
    @DisplayName("카드 발급")
    void takeCards() {
        Cards cards = new Cards(JACK, THREE);
        cards.take(ACE);
        assertThat(cards.bestScore()).isEqualTo(new Score(14));
    }
}
