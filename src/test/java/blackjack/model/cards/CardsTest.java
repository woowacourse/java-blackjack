package blackjack.model.cards;

import static blackjack.model.Suit.CLOVER;
import static blackjack.model.Suit.HEART;
import static blackjack.model.Suit.SPADE;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.Card;
import blackjack.model.Rank;
import blackjack.model.Score;
import blackjack.model.cards.Cards;
import blackjack.model.cards.ImmutableCards;
import blackjack.model.cards.OwnCards;
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
    void bestScore(OwnCards cards, int expect) {
        assertThat(cards.bestScore()).isEqualTo(new Score(expect));
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("불변 카드 최고 점수 계산")
    void immutableCardsBestScore(OwnCards cards, int expect) {
        Cards immutableCards = new ImmutableCards(cards);
        assertThat(immutableCards.bestScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(new OwnCards(ACE, JACK), 21),
                Arguments.of(new OwnCards(ACE, JACK, KING), 21),
                Arguments.of(new OwnCards(ACE, ACE, ACE, ACE), 14),
                Arguments.of(new OwnCards(QUEEN, JACK, KING), 30),
                Arguments.of(new OwnCards(THREE, TWO), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaxCards")
    @DisplayName("최대 카드 점수 계산")
    void maxScore(OwnCards cards, int expect) {
        assertThat(cards.maxScore()).isEqualTo(new Score(expect));
    }

    @ParameterizedTest
    @MethodSource("provideMaxCards")
    @DisplayName("최대 카드 점수 계산")
    void immutableMaxScore(OwnCards cards, int expect) {
        Cards immutableCards = new ImmutableCards(cards);
        assertThat(immutableCards.maxScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideMaxCards() {
        return Stream.of(
                Arguments.of(new OwnCards(ACE, JACK), 21),
                Arguments.of(new OwnCards(ACE, JACK, KING), 31),
                Arguments.of(new OwnCards(ACE, ACE, NINE), 31)
        );
    }

    @Test
    @DisplayName("카드 발급")
    void takeCards() {
        OwnCards cards = new OwnCards(JACK, THREE);
        cards.take(ACE);
        assertThat(cards.bestScore()).isEqualTo(new Score(14));
    }

    @Test
    @DisplayName("불변 카드 상태 변경 시 예외 발생")
    void immutableCards() {
        Cards immutableCards = new ImmutableCards(new OwnCards(ACE, JACK));
        assertThatThrownBy(() -> immutableCards.take(ACE))
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
