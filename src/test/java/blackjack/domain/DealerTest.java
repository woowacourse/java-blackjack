package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card.Shape;
import blackjack.domain.Card.Value;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 더 뽑을 수 있다")
    @Test
    void isDrawableTest_whenScoreIsUnder16_returnTrue() {
        List<Card> CardsScore16 = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.SIX, Shape.HEART));
        Dealer dealer = new Dealer(CardsScore16);

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("카드의 총 점수가 17을 넘으면, 카드를 더 뽑을 수 없다")
    @Test
    void isDrawableTest_whenScoreIsOver17_returnFalse() {
        List<Card> CardsScore17 = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.SEVEN, Shape.HEART));
        Dealer dealer = new Dealer(CardsScore17);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    // TODO Fixture로 추출하기
    static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Value.ACE, Shape.HEART),
                                new Card(Value.KING, Shape.HEART)
                        ), 21),
                Arguments.of(
                        List.of(
                                new Card(Value.ACE, Shape.HEART),
                                new Card(Value.ACE, Shape.SPADE)
                        ), 12),
                Arguments.of(
                        List.of(
                                new Card(Value.ACE, Shape.HEART),
                                new Card(Value.KING, Shape.HEART),
                                new Card(Value.TWO, Shape.HEART)
                        ), 13),
                Arguments.of(
                        List.of(
                                new Card(Value.KING, Shape.HEART),
                                new Card(Value.TWO, Shape.HEART)
                        ), 12)
        );
    }
}
