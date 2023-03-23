package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitTest {
    @Test
    @DisplayName("블랙잭 게임 카드 문양은 하트, 다이아몬드, 클로버, 스페이드의 값만 가진다.")
    void generatePatternTest() {
        List<Suit> suits = new ArrayList<>(Arrays.asList(Suit.values()));
        List<String> answers = List.of("하트", "다이아몬드", "클로버", "스페이드");

        IntStream.range(0, answers.size())
                .forEach(index -> Assertions.assertThat(suits.get(index).getSuit()).isEqualTo(answers.get(index)));
    }
}
