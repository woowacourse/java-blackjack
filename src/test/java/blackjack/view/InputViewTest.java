package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    @Test
    @DisplayName("입력받은 이름을 List 형태로 반환한다.")
    void readPlayerNames() {
        String names = "몰리,리브";
        assertThat(InputView.readPlayerNames(() -> names)).containsExactly("몰리", "리브");
    }
}
