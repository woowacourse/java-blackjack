package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("canReceiveCardTestCased")
    @DisplayName("카드를 받을 수 있을지 확인한다.")
    void canReceiveCard(String description, List<Number> numbers, boolean expected) {
        Cards cards = createCards(numbers);
        assertThat(expected).isEqualTo(cards.canReceiveCard(21));
    }

    static Stream<Arguments> canReceiveCardTestCased() {
        return Stream.of(
                Arguments.of("Ace 2개면 카드를 받을 수 있다.",
                        List.of(Number.ACE, Number.ACE), true),
                Arguments.of("King,9,Ace,Ace면 카드를 받을 수 있다.",
                        List.of(Number.KING, Number.NINE, Number.ACE, Number.ACE), true),
                Arguments.of("King 3개면 카드를 받을 수 없다.",
                        List.of(Number.TEN, Number.TEN, Number.TEN), false)
        );
    }

    @Test
    @DisplayName("Ace를 두 장을 받을 경우의 점수 합은 12가 된다.")
    void calculateOptimalScore() {
        List<Number> numbers = new ArrayList<>(List.of(Number.ACE, Number.ACE));
        Cards cards = createCards(numbers);

        assertThat(12).isEqualTo(cards.calculateOptimalScore());
    }

    private Cards createCards(List<Number> numbers) {
        Cards cards = new Cards(new ArrayList<>());
        numbers.forEach(number -> cards.addCard(new Card(Shape.SPADE, number)));
        return cards;
    }

    @Test
    @DisplayName("버스트 후 Ace를 1로 조정한 점수를 반환한다.")
    void calculateOptimalScore_aceAdjustment() {
        Cards cards = createCards(
                List.of(Number.ACE, Number.JACK, Number.QUEEN));
        assertThat(cards.calculateOptimalScore()).isEqualTo(21);
    }
}
