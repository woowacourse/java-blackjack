package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {

    @ParameterizedTest
    @MethodSource("getPair")
    @DisplayName("카드 점수 반환한다.")
    void checkCardScore(Score score, int value){
        Card card = new Card(Type.SPADE, score);
        assertThat(card.getScore().getAmount()).isEqualTo(value);
    }

    private static Stream<Arguments> getPair() {
        return Stream.of(
                Arguments.of(Score.ACE, 1),
                Arguments.of(Score.TWO, 2),
                Arguments.of(Score.THREE, 3),
                Arguments.of(Score.FOUR, 4),
                Arguments.of(Score.FIVE, 5),
                Arguments.of(Score.SIX, 6),
                Arguments.of(Score.SEVEN, 7),
                Arguments.of(Score.EIGHT, 8),
                Arguments.of(Score.NINE, 9),
                Arguments.of(Score.TEN, 10),
                Arguments.of(Score.JACK, 10),
                Arguments.of(Score.KING, 10),
                Arguments.of(Score.QUEEN, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("getTypeName")
    @DisplayName("카드 타입 이름을 반환한다.")
    void checkCardScore(Type type, String name){
        Card card = new Card(type, Score.ACE);
        assertThat(card.getType().getName()).isEqualTo(name);
    }

    private static Stream<Arguments> getTypeName() {
        return Stream.of(
                Arguments.of(Type.SPADE, "스페이드"),
                Arguments.of(Type.DIAMOND, "다이아몬드"),
                Arguments.of(Type.CLOVER, "클로버"),
                Arguments.of(Type.HEART, "하트")
        );
    }
}
