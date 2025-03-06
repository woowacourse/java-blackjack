package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListMergerTest {

    @Test
    @DisplayName("단일 요소와 리스트를 하나의 리스트로 합칠 수 있다")
    void combineTest() {
        String a = "apple";
        List<String> b = List.of("banana", "cupcake", "delicious", "eggmayo");

        assertThat(ListMerger.combine(a, b)).containsExactly("apple", "banana", "cupcake", "delicious", "eggmayo");
    }
}
