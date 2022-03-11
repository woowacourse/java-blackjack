package blackjack.model.cards;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.blackjack.Score;
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
    void bestScore(ScoreCards cards, int expect) {
        assertThat(cards.score()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(Cards.bestScoreCards(Cards.of(ACE, JACK)), 21),
                Arguments.of(Cards.bestScoreCards(Cards.of(ACE, JACK, KING)), 21),
                Arguments.of(Cards.bestScoreCards(Cards.of(ACE, ACE, ACE, ACE)), 14),
                Arguments.of(Cards.bestScoreCards(Cards.of(QUEEN, JACK, KING)), 30),
                Arguments.of(Cards.bestScoreCards(Cards.of(THREE, TWO)), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaxCards")
    @DisplayName("최대 카드 점수 계산")
    void maxScore(ScoreCards cards, int expect) {
        assertThat(cards.score()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideMaxCards() {
        return Stream.of(
                Arguments.of(Cards.maxScoreCards(Cards.of(ACE, JACK)), 21),
                Arguments.of(Cards.maxScoreCards(Cards.of(ACE, JACK, KING)), 31),
                Arguments.of(Cards.maxScoreCards(Cards.of(ACE, ACE, NINE)), 31)
        );
    }

    @Test
    @DisplayName("불변 카드 상태 변경 시 예외 발생")
    void immutableCards() {
        Cards immutableCards = Cards.toUnmodifiable(Cards.of(ACE, JACK));
        assertThatThrownBy(() -> immutableCards.take(ACE))
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
