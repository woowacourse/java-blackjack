package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {

    @DisplayName("카드를 추가할 수 있다")
    @Test
    void test1() {
        //given
        Hand hand = Hand.empty();
        Card card = new Card(CardNumber.TWO, CardShape.CLOVER);

        Hand expected = Hand.of(List.of(card));
        //when
        hand.add(card);
        //then
        assertThat(hand).isEqualTo(expected);
    }

    @DisplayName("숫자 카드의 합을 구할 수 있다")
    @ParameterizedTest
    @MethodSource("createNumberCardsCase")
    void 숫자_카드_합_구하기(CardNumber number1, CardNumber number2, int expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Hand hand = Hand.of(List.of(card1, card2));
        //when
        int actual = hand.calculateFinalScore();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createNumberCardsCase() {
        return Stream.of(
                Arguments.of(CardNumber.TWO, CardNumber.FOUR, 6),
                Arguments.of(CardNumber.TWO, CardNumber.THREE, 5),
                Arguments.of(CardNumber.TWO, CardNumber.TWO, 4),
                Arguments.of(CardNumber.TWO, CardNumber.A, 13)
        );
    }

    @DisplayName("A를 제외한 문자 카드의 합을 구할 수 있다")
    @ParameterizedTest
    @MethodSource("createCharCardsCase")
    void 문자_카드_합_구하기(CardNumber number1, CardNumber number2, int expected) {
        //given
        Card card1 = new Card(number1, CardShape.CLOVER);
        Card card2 = new Card(number2, CardShape.CLOVER);
        Hand hand = Hand.of(List.of(card1, card2));

        //when
        int actual = hand.calculateFinalScore();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createCharCardsCase() {
        return Stream.of(
                Arguments.of(CardNumber.KING, CardNumber.THREE, 13),
                Arguments.of(CardNumber.JACK, CardNumber.TEN, 20),
                Arguments.of(CardNumber.QUEEN, CardNumber.KING, 20)
        );
    }

    @DisplayName("21 이하일 때, 최적의 결과를 선택할 수 있다.")
    @ParameterizedTest
    @MethodSource("createCardsCase")
    void 최적_결과_선택_21_이하(List<Card> inputCards, int expected) {
        // given
        Hand hand = Hand.of(inputCards);

        // when
        final int score = hand.calculateFinalScore();

        // then
        assertThat(score).isEqualTo(expected);
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
        Hand hand = Hand.of(inputCards);

        // when
        final int score = hand.calculateFinalScore();

        //then
        assertThat(score).isEqualTo(expected);
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

    @DisplayName("플레이어가 소유한 카드에 따라서 bust 여부를 판단한다.")
    @ParameterizedTest
    @MethodSource("createBurstCase")
    void test1(List<Card> inputCard, boolean expected) {
        // given
        Hand hand = Hand.of(inputCard);

        // when
        final boolean actual = hand.isBust();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createBurstCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        false
                )
        );
    }

    @DisplayName("플레이어가 소유한 카드에 따라서 21 여부를 판단한다.")
    @ParameterizedTest
    @MethodSource("create21Case")
    void 합이_21인지_검증(List<Card> inputCard, boolean expected) {
        // given
        Hand hand = Hand.of(inputCard);

        // when
        final boolean actual = hand.isTwentyOne();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> create21Case() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.TEN, CardShape.CLOVER)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.CLOVER)
                        ),
                        false
                )
        );
    }

    @DisplayName("플레이어가 소유한 카드에 따라서 블랙잭 여부를 판단한다.")
    @ParameterizedTest
    @MethodSource("createBlackJackCase")
    void 블랙잭인지_검증(List<Card> inputCard, boolean expected) {
        // given
        Hand hand = Hand.of(inputCard);

        // when
        final boolean actual = hand.isBlackJack();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> createBlackJackCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.TEN, CardShape.CLOVER)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.CLOVER)
                        ),
                        false
                )
        );
    }

    @DisplayName("블랙잭일 때, 카드를 추가하면 예외를 발생시킨다.")
    @Test
    void 카드_추가_예외() {
        // given
        List<Card> cards = List.of(
                new Card(CardNumber.A, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Hand hand = Hand.of(cards);

        // when & then
        assertThatThrownBy(() -> hand.add(new Card(CardNumber.TWO, CardShape.CLOVER)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 더 받을 수 없습니다.");
    }

    @DisplayName("버스트일 때, 카드를 추가하면 예외를 발생시킨다.")
    @Test
    void 카드_추가_예외2() {
        // given
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Hand hand = Hand.of(cards);

        // when & then
        assertThatThrownBy(() -> hand.add(new Card(CardNumber.TWO, CardShape.CLOVER)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 더 받을 수 없습니다.");
    }
}
