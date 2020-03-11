package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SuitTest {
    @ParameterizedTest
    @DisplayName("Suit 이름이 잘 불려오는지 확인")
    @CsvSource(value = {"HEART,하트", "SPADE,스페이드", "CLUB,클럽", "DIAMOND,다이아몬드"})
    void checkSuitName(Suit suit, String expected) {
        assertThat(suit.getName()).isEqualTo(expected);
    }
}
