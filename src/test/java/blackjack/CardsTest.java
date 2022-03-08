package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @Test
    void 카드_합_계산() {
        Cards cards = new Cards("1다이아몬드", "2다이아몬드");
        assertThat(cards.score()).isEqualTo(3);
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

}
