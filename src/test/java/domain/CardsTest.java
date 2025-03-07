package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import java.util.List;
import java.util.Set;
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
        assertThat(cards.getCards()).contains(card);
    }

    @DisplayName("숫자 카드의 합을 구할 수 있다")
    @ParameterizedTest
    @MethodSource("createNumberCardsCase")
    void 숫자_카드_합_구하기(CardNumber number1, CardNumber number2, Set<Integer> expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2));
        //when
        Set<Integer> actual = cards.getCoordinateSums();
        //then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private static Stream createNumberCardsCase() {
        return Stream.of(
                Arguments.of(CardNumber.TWO, CardNumber.FOUR, Set.of(6)),
                Arguments.of(CardNumber.TWO, CardNumber.THREE, Set.of(5)),
                Arguments.of(CardNumber.TWO, CardNumber.FIVE, Set.of(7)),
                Arguments.of(CardNumber.TWO, CardNumber.FOUR, Set.of(6))
        );
    }

    @DisplayName("A를 제외한 문자 카드의 합을 구할 수 있다")
    @ParameterizedTest
    @MethodSource("createCharCardsCase")
    void 문자_카드_합_구하기(CardNumber number1, CardNumber number2, Set<Integer> expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2));

        //when
        Set<Integer> actual = cards.getCoordinateSums();

        //then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private static Stream createCharCardsCase() {
        return Stream.of(
                Arguments.of(CardNumber.KING, CardNumber.THREE, Set.of(13)),
                Arguments.of(CardNumber.JACK, CardNumber.TEN, Set.of(20)),
                Arguments.of(CardNumber.QUEEN, CardNumber.KING, Set.of(20))
        );
    }

    @DisplayName("21에 가장 가까운 합을 구한다")
    @ParameterizedTest
    @MethodSource("createAceCardsCase")
    void 문자_카드_합_구하기(CardNumber number1, CardNumber number2, CardNumber number3, Set<Integer> expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Card card3 = new Card(number3, CardShape.CLOVER);
        Cards cards = Cards.of(List.of(card1, card2, card3));

        //when
        Set<Integer> actual = cards.getCoordinateSums();

        //then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private static Stream createAceCardsCase() {
        return Stream.of(
                Arguments.of(CardNumber.A, CardNumber.JACK, CardNumber.TEN, Set.of(21, 31)),
                Arguments.of(CardNumber.A, CardNumber.QUEEN, CardNumber.FIVE, Set.of(16, 26)),
                Arguments.of(CardNumber.A, CardNumber.SIX, CardNumber.FIVE, Set.of(12, 22)),
                Arguments.of(CardNumber.A, CardNumber.A, CardNumber.A, Set.of(3, 13, 23, 33))
        );
    }
}
