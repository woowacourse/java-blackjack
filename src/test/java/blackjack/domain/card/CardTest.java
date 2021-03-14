package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardTest {
    @Test
    @DisplayName("카드 생성")
    void create_cards1() {
        assertThat(Card.from("A다이아몬드")).isSameAs(Card.from("A다이아몬드"));
    }

    @Test
    @DisplayName("fail: 잘못된 카드 생성")
    void create_cards2() {
        assertThatThrownBy(() -> Card.from("11다이아몬드")).isInstanceOf(IllegalArgumentException.class);
    }
}
