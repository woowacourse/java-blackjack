package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("카드 한 장의 점수 확인")
    void getPoint() {
        assertThat(Card.of("스페이드", "A").getPoint()).isEqualTo(1);
        assertThat(Card.of("스페이드", "K").getPoint()).isEqualTo(10);
    }

    @Test
    @DisplayName("심볼과 타입정보 출력 확인")
    void getCardInfo() {
        assertThat(Card.of("스페이드", "A").getCardInfo()).isEqualTo("A스페이드");
    }
}
