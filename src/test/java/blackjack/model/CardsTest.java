package blackjack.model;

import static blackjack.model.Rank.ACE;
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

import blackjack.model.Card;
import blackjack.model.Cards;
import blackjack.model.Score;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @ParameterizedTest
    @MethodSource("provideMixHandCards")
    @DisplayName("MixHand 카드 점수 계산")
    void mixHandScore(Cards cards, int expect) {
        assertThat(cards.bestScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideMixHandCards() {
        return Stream.of(
                Arguments.of(new Cards(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(new Cards(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                        new Card(KING, CLOVER)), 21),
                Arguments.of(new Cards(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                        new Card(NINE, CLOVER)), 21),
                Arguments.of(new Cards(new Card(QUEEN, CLOVER), new Card(JACK, HEART),
                        new Card(KING, DIAMOND)), 30),
                Arguments.of(new Cards(new Card(THREE, DIAMOND), new Card(TWO, DIAMOND)), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSoftHandCards")
    @DisplayName("SoftHand 카드 점수 계산")
    void softHandScore(Cards cards, int expect) {
        assertThat(cards.maxScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideSoftHandCards() {
        return Stream.of(
                Arguments.of(new Cards(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(new Cards(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                        new Card(KING, CLOVER)), 31),
                Arguments.of(new Cards(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                        new Card(NINE, CLOVER)), 31)
        );
    }

    @Test
    @DisplayName("카드 발급")
    void takeCards() {
        Cards cards = new Cards(new Card(JACK, DIAMOND), new Card(THREE, CLOVER));
        cards.take(new Card(ACE, HEART));
        assertThat(cards.bestScore()).isEqualTo(new Score(14));
    }
}
