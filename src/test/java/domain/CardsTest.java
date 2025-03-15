package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @DisplayName("카드를 추가할 수 있다")
    @Test
    void test1() {
        //given
        Cards cards = Cards.empty();
        Card card = new Card(CardNumber.TWO, CardShape.CLOVER);
        //when
        cards.add(card);
        //then
        assertThat(cards.getValues()).contains(card);
    }

    @DisplayName("21 이하일 때, 최적의 결과를 선택할 수 있다.")
    @ParameterizedTest
    @MethodSource("createCardsCase")
    void 최적_결과_선택_21_이하(List<Card> inputCards, int expected) {
        // given
        Cards cards = Cards.of(inputCards);

        // when
        final Score score = cards.getScore();

        // then
        assertThat(score.value()).isEqualTo(expected);
    }

    private static Stream createCardsCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        21
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER),
                                new Card(CardNumber.FIVE, CardShape.CLOVER)
                        ),
                        16
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.DIAMOND)
                        ),
                        12
                )
        );
    }

    @DisplayName("21 초과할 때, 21에 가장 가까운 값을 선택할 수 있다")
    @ParameterizedTest
    @MethodSource("createBurstCardsCase")
    void 가장_가까운_값_선택(List<Card> inputCards, int expected) {
        // given
        Cards cards = Cards.of(inputCards);

        // when
        final Score score = cards.getScore();

        //then
        assertThat(score.value()).isEqualTo(expected);
    }

    private static Stream createBurstCardsCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        22
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER),
                                new Card(CardNumber.FIVE, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        26
                )
        );
    }
}
