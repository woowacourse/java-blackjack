package domain;

import static domain.Card.CLOVER_ACE;
import static domain.Card.CLOVER_THREE;
import static domain.Card.DIA_ACE;
import static domain.Card.DIA_JACK;
import static domain.Card.HEART_ACE;
import static domain.Card.HEART_TWO;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("중복된 카드가 있으면 예외를 반환합니다.")
    void validateDuplicateTest(List<Card> cards) {
        Assertions.assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드에 중복이 있습니다.");

    }

    public static Stream<Arguments> validateDuplicateTest() {
        return Stream.of(
                Arguments.of(List.of(HEART_TWO, HEART_TWO)),
                Arguments.of(List.of(CLOVER_ACE, CLOVER_ACE)),
                Arguments.of(List.of(DIA_JACK, DIA_JACK))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("두 카드에 같은 요소가 있는지 확인합니다.")
    void hasCommonCardTest(List<Card> cards, List<Card> otherCards) {
        Assertions.assertThat(new Cards(cards).hasCommonCard(new Cards(otherCards))).isTrue();
    }

    public static Stream<Arguments> hasCommonCardTest() {
        return Stream.of(
                Arguments.of(List.of(HEART_TWO, HEART_ACE), List.of(HEART_ACE)),
                Arguments.of(List.of(CLOVER_ACE, CLOVER_THREE), List.of(CLOVER_THREE)),
                Arguments.of(List.of(DIA_JACK, DIA_ACE), List.of(DIA_JACK))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("카드 숫자의 전체 합을 계산합니다.")
    void calculateCardScoreTest(List<Card> cards, int expected) {
        //given
        Cards newCards = new Cards(cards);

        //when
        final int cardScore = newCards.calculateScore();

        //then
        Assertions.assertThat(cardScore).isEqualTo(expected);
    }

    public static Stream<Arguments> calculateCardScoreTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), 14),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), 21),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), 12)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("카드가 21 초과(버스트) 라면 예외가 발생합니다.")
    void validateBustTest(List<Card> cards) {
        Cards originalCards = new Cards(List.of());
        Assertions.assertThatThrownBy(() -> originalCards.addCards(new Cards(cards)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("버스트입니다.");

    }

    public static Stream<Arguments> validateBustTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_THREE), 23),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), 22)
        );
    }
}
