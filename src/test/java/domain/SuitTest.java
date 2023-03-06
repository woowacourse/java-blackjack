package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitTest {
    @DisplayName("블랙잭 게임 카드 문양은 하트, 다이아몬드, 클로버, 스페이드의 값만 가진다.")
    @Test
    void generatePatternTest() {
        List<String> patterns = Arrays.stream(Suit.values())
                .map(Suit::getPattern)
                .collect(Collectors.toList());

        String[] expectedPatterns = {"하트", "다이아몬드", "클로버", "스페이드"};

        Assertions.assertThat(patterns).containsOnly(expectedPatterns);
    }
}
