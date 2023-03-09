package domain;

import domain.card.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("13개의 숫자가 존재한다.")
    @Test
    void 모든_숫자_존재() {
        String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        List<String> numberToPrints = Arrays.stream(Rank.values())
                .map(Rank::getNumberToPrint)
                .collect(Collectors.toList());
        assertThat(numberToPrints).containsExactlyInAnyOrder(numbers);
    }

    @DisplayName("각 카드는 점수를 가진다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE:1", "TWO:2", "THREE:3", "FOUR:4", "FIVE:5", "SIX:6", "SEVEN:7", "EIGHT:8", "NINE:9",
            "TEN:10", "JACK:10", "QUEEN:10", "KING:10"}, delimiter = ':')
    void 모든_카드_점수_존재(Rank rank, int score) {
        assertThat(rank.getScore()).isEqualTo(score);
    }
}