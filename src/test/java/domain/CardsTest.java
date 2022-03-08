package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @DisplayName("52개의 생성된 카드 개수가 올바른지 확인한다.")
    @Test
    void select_cards() {
        Cards cardMachine = new Cards();
        cardMachine.generate();

        assertThat(cardMachine.getCards().size()).isEqualTo(52);
    }

}
