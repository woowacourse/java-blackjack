package blackjack.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Pair;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListsTest {

    @Test
    @DisplayName("카테시안 곱 로직 테스트")
    void 카테시안_곱_로직_테스트() {
        List<String> list1 = List.of("a", "b");
        List<Integer> list2 = List.of(1, 2);
        List<Pair<String, Integer>> expected = List.of(
            new Pair<>("a", 1),
            new Pair<>("a", 2),
            new Pair<>("b", 1),
            new Pair<>("b", 2));

        List<Pair<String, Integer>> actual = Lists.cartesianProduct(list1, list2);

        assertThat(actual).usingRecursiveComparison()
            .isEqualTo(expected);
    }
}