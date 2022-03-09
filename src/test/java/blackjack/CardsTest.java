package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {

    @Test
    void 카드_합_계산() {
        Cards cards = new Cards("1다이아몬드", "2다이아몬드");
        assertThat(cards.score()).isEqualTo(new Score(3));
    }

    @Test
    void J_Q_K_카드_점수_계산() {
        Cards cards = new Cards("Q클로버", "J하트", "K다이아몬드");
        assertThat(cards.score()).isEqualTo(new Score(30));
    }

    @ParameterizedTest
    @MethodSource("provideAceData")
    void ACE_카드_점수_계산(List<String> input) {
        Cards cards = new Cards(input.toArray(String[]::new));
        assertThat(cards.score()).isEqualTo(new Score(21));
    }

    protected static Stream<Arguments> provideAceData() {
        return Stream.of(
                Arguments.of(List.of("A스페이스", "J하트")),
                Arguments.of(List.of("A다이아몬드", "J다이아몬드", "K클로버"))
        );
    }

    @ParameterizedTest(name = "입력값 : {1}")
    @MethodSource("provideBustFixture")
    void 버스트_발생(boolean expect, List<String> values) {
        Cards cards = new Cards(values.toArray(String[]::new));
        assertThat(cards.isBust()).isEqualTo(expect);
    }

    protected static Stream<Arguments> provideBustFixture() {
        return Stream.of(
            Arguments.of(true, List.of("J다이아몬드","Q다이아몬드","2클로버")),
            Arguments.of(false, List.of("A다이아몬드", "J다이아몬드", "4하트"))
        );
    }

    @Test
    void 카드_비교_테스트() {
        Cards cards1 = new Cards("1다이아몬드", "2하트");
        Cards cards2 = new Cards("3클로버", "4하트");
        assertThat(cards1.compare(cards2)).isEqualTo(Result.LOSS);
    }
}
