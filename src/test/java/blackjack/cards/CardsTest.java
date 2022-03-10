package blackjack.cards;

import static blackjack.Rank.ACE;
import static blackjack.Rank.JACK;
import static blackjack.Rank.KING;
import static blackjack.Rank.NINE;
import static blackjack.Rank.QUEEN;
import static blackjack.Rank.THREE;
import static blackjack.Rank.TWO;
import static blackjack.Suit.CLOVER;
import static blackjack.Suit.DIAMOND;
import static blackjack.Suit.HEART;
import static blackjack.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.Card;
import blackjack.Dealer;
import blackjack.Score;
import blackjack.cards.Cards;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
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
        assertThat(cards.score()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideMixHandCards() {
        return Stream.of(
                Arguments.of(Cards.mixHandCards(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(Cards.mixHandCards(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                        new Card(KING, CLOVER)), 21),
                Arguments.of(Cards.mixHandCards(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                        new Card(NINE, CLOVER)), 21),
                Arguments.of(Cards.mixHandCards(new Card(QUEEN, CLOVER), new Card(JACK, HEART),
                        new Card(KING, DIAMOND)), 30),
                Arguments.of(Cards.mixHandCards(new Card(THREE, DIAMOND), new Card(TWO, DIAMOND)), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSoftHandCards")
    @DisplayName("SoftHand 카드 점수 계산")
    void softHandScore(Cards cards, int expect) {
        assertThat(cards.score()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideSoftHandCards() {
        return Stream.of(
                Arguments.of(Cards.softHandCards(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(Cards.softHandCards(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                        new Card(KING, CLOVER)), 31),
                Arguments.of(Cards.softHandCards(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                        new Card(NINE, CLOVER)), 31)
        );
    }

    @Test
    @DisplayName("카드 발급")
    void takeCards() {
        Cards cards = new HardHandCards(new Card(JACK, DIAMOND), new Card(THREE, CLOVER));
        cards.take(new Card(ACE, HEART));
        assertThat(cards.score()).isEqualTo(new Score(14));
    }
}
