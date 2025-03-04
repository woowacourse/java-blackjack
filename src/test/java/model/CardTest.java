package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드의 숫자 계산은 카드 문양이 아닌 카드 숫자로 한다.")
    @Test
    void test1() {
        Cards cards = new Cards(List.of(
                new Card(3, "diamond"),
                new Card(9, "clover"),
                new Card(8, "diamond")
        ));
        assertThat(cards.calculateSum()).isEqualTo(20);
    }
}
