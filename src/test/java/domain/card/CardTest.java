package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    @DisplayName("Card 생성")
    void create() {
        assertThat(Card.of("스페이드", "A")).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("에이스 여부 확인")
    void isAce() {
        assertThat(Card.of("스페이드", "A").isAce()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("카드 한 장의 점수 확인")
    @CsvSource(value = {"A:1", "5:5", "K:10", "Q:10", "J:10", "10:10"}, delimiter = ':')
    void getPoint(String input, int expected) {
        assertThat(Card.of("스페이드", input).getPoint()).isEqualTo(expected);
    }

    @Test
    @DisplayName("심볼과 타입정보 출력 확인")
    void getCardInfo() {
        assertThat(Card.of("스페이드", "A").getCardInfo()).isEqualTo("A스페이드");
    }
}
